import fs from "node:fs";
import path from "node:path";
import type { ChapterStoryboardBundle, StoryboardBeat } from "../src/types.js";

const OLLAMA_URL = process.env.OLLAMA_URL || "http://127.0.0.1:11434";
const DEFAULT_MODEL = process.env.OLLAMA_MODEL || "qwen2.5:7b";

const VALID_KINDS = new Set([
  "hook",
  "code-spotlight",
  "flow-diagram",
  "terminal",
  "compare",
  "cta",
]);
const VALID_SFX = new Set(["none", "whoosh", "pop", "keyclick", "success", "error"]);

export interface ComplexityEstimate {
  wordCount: number;
  h2Count: number;
  codeBlockCount: number;
  recommendedParts: number;
}

export function estimateChapterComplexity(markdown: string): ComplexityEstimate {
  const words = markdown.trim().split(/\s+/).filter(Boolean).length;
  const h2Count = (markdown.match(/^##\s+/gm) || []).length;
  const codeBlockCount = (markdown.match(/^```/gm) || []).length / 2;

  let recommendedParts = 3;
  if (words >= 3400 || h2Count >= 9) {
    recommendedParts = 5;
  } else if (words >= 2100 || h2Count >= 6) {
    recommendedParts = 4;
  }

  return {
    wordCount: words,
    h2Count,
    codeBlockCount: Math.floor(codeBlockCount),
    recommendedParts,
  };
}

function splitMarkdownIntoPartSlices(markdown: string, numParts: number): string[] {
  // Strip YAML frontmatter
  const body = markdown.replace(/^---[\s\S]*?---\s*/, "").trim();
  const sections = body
    .split(/(?=^##\s+)/gm)
    .map((s) => s.trim())
    .filter(Boolean);

  if (sections.length <= 1) {
    const chunkSize = Math.ceil(body.length / numParts);
    return Array.from({ length: numParts }, (_, i) =>
      body.slice(i * chunkSize, (i + 1) * chunkSize).slice(0, 3800)
    );
  }

  const slices: string[] = Array.from({ length: numParts }, () => "");
  sections.forEach((sec, idx) => {
    const bucket = Math.min(numParts - 1, Math.floor((idx * numParts) / sections.length));
    slices[bucket] += (slices[bucket] ? "\n\n" : "") + sec;
  });

  return slices.map((s, idx) => {
    const content = s || sections[Math.min(idx, sections.length - 1)] || body;
    return content.length > 3800 ? `${content.slice(0, 3800)}\n[...]` : content;
  });
}

type PartPayload = ChapterStoryboardBundle["parts"][number];

function validatePart(part: unknown, partNumber: number): string[] {
  const errors: string[] = [];
  if (!part || typeof part !== "object") {
    return [`Part ${partNumber} must be a JSON object.`];
  }
  const p = part as Partial<PartPayload>;
  if (!p.partSlug || typeof p.partSlug !== "string") {
    errors.push(`Part ${partNumber} missing partSlug.`);
  }
  if (!p.partTitle || typeof p.partTitle !== "string") {
    errors.push(`Part ${partNumber} missing partTitle.`);
  }
  if (!Array.isArray(p.beats) || p.beats.length < 4 || p.beats.length > 6) {
    errors.push(
      `Part ${partNumber} must have 4–6 beats, got ${Array.isArray(p.beats) ? p.beats.length : 0}.`
    );
    return errors;
  }

  p.beats.forEach((beat: StoryboardBeat, bIdx: number) => {
    const tag = `Part ${partNumber} beat ${bIdx + 1} (${beat?.id || "?"})`;
    if (!beat || !VALID_KINDS.has(beat.kind)) {
      errors.push(`${tag}: invalid kind "${String(beat?.kind)}".`);
      return;
    }
    if (!beat.narration || typeof beat.narration !== "string") {
      errors.push(`${tag}: missing narration.`);
    } else {
      const wc = beat.narration.trim().split(/\s+/).length;
      if (wc > 52) {
        errors.push(`${tag}: narration too long (${wc} words; keep <= 42 words).`);
      }
      if (/\blive\s+(inside|in|on|within|under|outside)\b/i.test(beat.narration)) {
        errors.push(
          `${tag}: contains homograph trap "live inside/in/on" — use "sit/belong/reside".`
        );
      }
    }
    if (beat.sfx && !VALID_SFX.has(beat.sfx)) {
      errors.push(`${tag}: invalid sfx "${beat.sfx}".`);
    }
    if (beat.kind === "hook") {
      if (!beat.eyebrow || !beat.headline || !beat.subline) {
        errors.push(`${tag}: hook requires eyebrow, headline, subline.`);
      }
    }
    if (beat.kind === "code-spotlight") {
      const lines = (beat.code || "").split("\n");
      if (!beat.code || lines.length > 13) {
        errors.push(`${tag}: code-spotlight code must be 1–12 lines (got ${lines.length}).`);
      }
      if (!Array.isArray(beat.steps) || beat.steps.length === 0) {
        errors.push(`${tag}: code-spotlight must have at least 1 step.`);
      } else {
        for (const st of beat.steps) {
          for (const ln of st.lines || []) {
            if (ln < 1 || ln > lines.length) {
              errors.push(`${tag}: step line ${ln} out of bounds (1..${lines.length}).`);
            }
          }
        }
      }
    }
    if (beat.kind === "flow-diagram") {
      if (!Array.isArray(beat.nodes) || beat.nodes.length < 2 || beat.nodes.length > 5) {
        errors.push(`${tag}: flow-diagram must have 2–5 nodes.`);
      }
    }
    if (beat.kind === "terminal") {
      if (!Array.isArray(beat.lines) || beat.lines.length < 2 || beat.lines.length > 10) {
        errors.push(`${tag}: terminal must have 2–10 lines.`);
      }
    }
    if (beat.kind === "compare") {
      if (!beat.top?.label || !beat.bottom?.label) {
        errors.push(`${tag}: compare must have top and bottom with label, badge, tone.`);
      }
    }
    if (beat.kind === "cta") {
      if (!Array.isArray(beat.takeaways) || beat.takeaways.length < 2) {
        errors.push(`${tag}: cta must have 2–4 takeaways.`);
      }
    }
  });

  return errors;
}

async function callOllamaJson(
  prompt: string,
  model: string,
  label: string
): Promise<string> {
  const startedAt = Date.now();
  let gotFirstToken = false;
  const prefillTicker = setInterval(() => {
    if (!gotFirstToken) {
      const sec = ((Date.now() - startedAt) / 1000).toFixed(0);
      console.log(`[Ollama] ${label} | prefilling prompt... (${sec}s)`);
    }
  }, 5000);

  let res: Response;
  try {
    res = await fetch(`${OLLAMA_URL}/api/generate`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        model,
        prompt,
        format: "json",
        stream: true,
        options: {
          temperature: 0.4,
          num_ctx: 4096,
          num_predict: 1150,
          repeat_penalty: 1.12,
        },
      }),
    });
  } catch (err) {
    clearInterval(prefillTicker);
    throw err;
  }

  if (!res.ok || !res.body) {
    clearInterval(prefillTicker);
    const text = await res.text();
    throw new Error(`Ollama API error (${res.status}): ${text}`);
  }

  const reader = res.body.getReader();
  const decoder = new TextDecoder();
  let buffer = "";
  let fullResponse = "";
  let tokenCount = 0;
  let lastLogAt = Date.now();

  try {
    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      buffer += decoder.decode(value, { stream: true });
      const lines = buffer.split("\n");
      buffer = lines.pop() ?? "";

      for (const line of lines) {
        const trimmed = line.trim();
        if (!trimmed) continue;
        const chunk = JSON.parse(trimmed) as { response?: string; done?: boolean };
        if (chunk.response) {
          if (!gotFirstToken) {
            gotFirstToken = true;
            clearInterval(prefillTicker);
            const ttft = ((Date.now() - startedAt) / 1000).toFixed(1);
            console.log(`[Ollama] ${label} | first token in ${ttft}s — streaming...`);
            lastLogAt = Date.now();
          }
          fullResponse += chunk.response;
          tokenCount++;
        }
        const now = Date.now();
        if (gotFirstToken && now - lastLogAt >= 3000) {
          const elapsedSec = ((now - startedAt) / 1000).toFixed(1);
          const tokPerSec = (
            tokenCount / Math.max(0.1, (now - startedAt) / 1000)
          ).toFixed(1);
          console.log(
            `[Ollama] ${label} | ${tokenCount} tokens | ${elapsedSec}s | ${tokPerSec} tok/s`
          );
          lastLogAt = now;
        }
      }
    }
  } finally {
    clearInterval(prefillTicker);
  }

  const totalSec = ((Date.now() - startedAt) / 1000).toFixed(1);
  console.log(`[Ollama] ${label} finished (${tokenCount} tokens in ${totalSec}s)`);

  if (!fullResponse) {
    throw new Error("Ollama returned empty response.");
  }
  return fullResponse;
}

export async function checkOllamaReady(model = DEFAULT_MODEL): Promise<void> {
  try {
    const res = await fetch(`${OLLAMA_URL}/api/tags`);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    const data = (await res.json()) as { models?: Array<{ name: string }> };
    const names = (data.models || []).map((m) => m.name);
    const hasModel = names.some((n) => n === model || n.startsWith(`${model}:`));
    if (!hasModel) {
      throw new Error(
        `Ollama is running, but model "${model}" is not pulled. Run: ollama pull ${model}`
      );
    }
  } catch (err) {
    const msg = err instanceof Error ? err.message : String(err);
    throw new Error(
      `Local Ollama not ready at ${OLLAMA_URL} (${msg}).\nInstall & start Ollama:\n  winget install Ollama.Ollama\n  ollama pull ${model}`
    );
  }
}

export async function generateStoryboardWithOllama(params: {
  hubDir: string;
  chapterId: string;
  referenceStoryboardPath: string;
  outStoryboardPath: string;
  model?: string;
}): Promise<ChapterStoryboardBundle> {
  const { hubDir, chapterId, referenceStoryboardPath, outStoryboardPath } = params;
  const model = params.model || DEFAULT_MODEL;
  const [level, slug] = chapterId.split("/");
  const levelNum = level.replace(/\D/g, "");
  const levelLabel = `Level ${levelNum}`;

  const mdPath = path.join(hubDir, "content", "roadmap", level, `${slug}.md`);
  const markdown = fs.readFileSync(mdPath, "utf-8");
  const complexity = estimateChapterComplexity(markdown);
  const titleMatch = markdown.match(/^title:\s*["']?(.+?)["']?\s*$/m);
  const chapterTitle = titleMatch ? titleMatch[1].trim() : slug.replace(/-/g, " ");

  const slices = splitMarkdownIntoPartSlices(markdown, complexity.recommendedParts);
  const parts: PartPayload[] = [];

  console.log(
    `[Ollama:${model}] Drafting ${chapterId} (${complexity.recommendedParts} parts, ${complexity.wordCount}w, per-part mode)...`
  );

  for (let pIdx = 0; pIdx < complexity.recommendedParts; pIdx++) {
    const partNumber = pIdx + 1;
    const isLast = partNumber === complexity.recommendedParts;
    const slice = slices[pIdx];

    const basePrompt = `You are a senior Java engineer scripting ONE 9:16 YouTube Short (Part ${partNumber} of ${complexity.recommendedParts}) for JavaHub chapter "${chapterTitle}" (${levelLabel}).
Output ONLY a single JSON object (no wrapper, no markdown fences).

Top-level fields:
{
  "partNumber": ${partNumber},
  "partSlug": "part-${partNumber}-<short-kebab-slug>",
  "partTitle": "<crisp title>",
  "beats": [ ...EXACTLY 5 beats... ]
}

EXACT schema for each beat kind (use these EXACT property names):
1. "hook": {"id":"p${partNumber}-b1-hook","kind":"hook","eyebrow":"${chapterTitle} • Part ${partNumber}/${complexity.recommendedParts}","headline":"...","highlightWord":"...","subline":"...","narration":"...","durationSec":9,"sfx":"pop"}
2. "code-spotlight": {"id":"...","kind":"code-spotlight","title":"...","filename":"Example.java","code":"line1\\nline2","steps":[{"lines":[1],"badge":"...","explanation":"...","accent":"emerald"}],"narration":"...","durationSec":13,"sfx":"keyclick"}
3. "flow-diagram": {"id":"...","kind":"flow-diagram","title":"...","subtitle":"...","nodes":[{"label":"...","detail":"...","tag":"...","accent":"sky"}],"narration":"...","durationSec":13,"sfx":"whoosh"}
4. "terminal": {"id":"...","kind":"terminal","title":"...","caption":"...","lines":[{"kind":"command","text":"javac Main.java"},{"kind":"success","text":"Output"}],"narration":"...","durationSec":12,"sfx":"keyclick"}
5. "compare": {"id":"...","kind":"compare","title":"...","top":{"label":"...","badge":"❌ BAD","tone":"bad","code":"...","points":["..."]},"bottom":{"label":"...","badge":"✅ GOOD","tone":"good","code":"...","points":["..."]},"narration":"...","durationSec":13,"sfx":"whoosh"}
6. "cta": {"id":"p${partNumber}-b5-cta","kind":"cta","title":"...","takeaways":["...","...","..."],${
      isLast
        ? `"command":"git checkout topic/${chapterId}","nextPartTeaser":"Full guide + JUnit 5 tests at ravishankar2463.github.io/java_hub"`
        : `"nextPartTeaser":"Up Next (Part ${partNumber + 1}): ..."`
    },"narration":"...","durationSec":8,"sfx":"success"}

Rules:
- Beat 1 MUST be "hook", Beats 2-4 mix of "code-spotlight"/"flow-diagram"/"terminal"/"compare", Beat 5 MUST be "cta".
- Keep code-spotlight "code" <= 12 lines; every steps[].lines entry MUST be 1..lineCount.
- Every terminal MUST have "lines" array (2-8 items, each with "kind" in "command"|"output"|"success"|"error"|"comment" and "text").
- Every flow-diagram MUST have "nodes" array (3-4 items).
- Every narration MUST be 16-38 words (NEVER >45 words), conversational spoken English. NEVER use verb "live" (use "sit"/"belong"/"reside").

Source Material for Part ${partNumber}/${complexity.recommendedParts}:
${slice}`;

    let lastErrors: string[] = [];
    let lastRaw = "";
    let validPart: PartPayload | null = null;

    for (let attempt = 1; attempt <= 3; attempt++) {
      const prompt =
        attempt === 1
          ? basePrompt
          : `${basePrompt}\n\nFix ALL validation errors from your previous JSON and output ONLY valid JSON:\n- ${lastErrors.join("\n- ")}\n\nPrevious JSON:\n${lastRaw.slice(0, 2800)}`;

      lastRaw = await callOllamaJson(
        prompt,
        model,
        `${chapterId} P${partNumber}/${complexity.recommendedParts} (try ${attempt}/3)`
      );

      try {
        const parsed = JSON.parse(lastRaw) as PartPayload;
        parsed.partNumber = partNumber;
        // Normalize common LLM key aliases (type -> kind, wrapped beats, line bounds)
        if (Array.isArray(parsed.beats)) {
          parsed.beats = parsed.beats.map((rawBeat: unknown, idx: number) => {
            let obj = (rawBeat && typeof rawBeat === "object" ? { ...(rawBeat as Record<string, unknown>) } : {}) as Record<string, unknown>;
            const keys = Object.keys(obj);
            if (keys.length === 1 && VALID_KINDS.has(keys[0]) && obj[keys[0]] && typeof obj[keys[0]] === "object") {
              obj = { kind: keys[0], ...(obj[keys[0]] as Record<string, unknown>) };
            }
            if (!obj.kind && typeof obj.type === "string" && VALID_KINDS.has(obj.type)) {
              obj.kind = obj.type;
            }
            if (!obj.kind) {
              if ("headline" in obj) obj.kind = "hook";
              else if ("code" in obj && "steps" in obj) obj.kind = "code-spotlight";
              else if ("nodes" in obj) obj.kind = "flow-diagram";
              else if ("lines" in obj) obj.kind = "terminal";
              else if ("top" in obj && "bottom" in obj) obj.kind = "compare";
              else if ("takeaways" in obj) obj.kind = "cta";
            }
            const b = obj as unknown as StoryboardBeat;
            if (!b.id) b.id = `p${partNumber}-b${idx + 1}-${b.kind || "beat"}`;
            if (typeof b.durationSec !== "number") b.durationSec = 10;
            if (!b.sfx || !VALID_SFX.has(b.sfx)) b.sfx = "whoosh";
            if (b.kind === "code-spotlight" && typeof b.code === "string") {
              const codeLines = b.code.split("\n");
              if (codeLines.length > 12) {
                b.code = codeLines.slice(0, 12).join("\n");
              }
              const maxLine = b.code.split("\n").length;
              if (Array.isArray(b.steps)) {
                b.steps.forEach((st) => {
                  if (Array.isArray(st.lines)) {
                    st.lines = st.lines
                      .map((n) => Math.max(1, Math.min(maxLine, Number(n) || 1)))
                      .filter((v, i, arr) => arr.indexOf(v) === i);
                  }
                });
              }
            }
            if (b.kind === "terminal" && Array.isArray(b.lines) && b.lines.length > 10) {
              b.lines = b.lines.slice(0, 10);
            }
            return b;
          });
        }
        lastErrors = validatePart(parsed, partNumber);
        if (lastErrors.length === 0) {
          validPart = parsed;
          break;
        }
        console.warn(
          `[Ollama] P${partNumber} attempt ${attempt} had ${lastErrors.length} issue(s): ${lastErrors[0]}`
        );
      } catch (err) {
        lastErrors = [`JSON.parse failed: ${err instanceof Error ? err.message : String(err)}`];
        console.warn(`[Ollama] P${partNumber} attempt ${attempt} invalid JSON: ${lastErrors[0]}`);
      }
    }

    if (!validPart) {
      throw new Error(
        `Failed Part ${partNumber}/${complexity.recommendedParts} of ${chapterId} after 3 attempts:\n${lastErrors.join("\n")}`
      );
    }
    parts.push(validPart);
  }

  const bundle: ChapterStoryboardBundle = {
    chapterId,
    level,
    levelLabel,
    slug,
    chapterTitle,
    roadmapUrl: `https://ravishankar2463.github.io/java_hub/roadmap/${chapterId}`,
    branchName: `topic/${chapterId}`,
    voice: "af_heart",
    speed: 1.24,
    parts,
  };

  fs.mkdirSync(path.dirname(outStoryboardPath), { recursive: true });
  fs.writeFileSync(outStoryboardPath, JSON.stringify(bundle, null, 2), "utf-8");
  console.log(`[Ollama] Validated & saved ${outStoryboardPath} (${parts.length} parts)`);
  return bundle;
}
