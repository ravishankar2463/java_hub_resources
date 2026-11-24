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

  return { wordCount: words, h2Count, codeBlockCount: Math.floor(codeBlockCount), recommendedParts };
}

export function validateStoryboardBundle(
  data: unknown,
  expectedChapterId: string,
  minParts: number
): string[] {
  const errors: string[] = [];
  if (!data || typeof data !== "object") {
    return ["Root JSON must be an object."];
  }
  const b = data as Partial<ChapterStoryboardBundle>;
  if (b.chapterId !== expectedChapterId) {
    errors.push(`chapterId must be "${expectedChapterId}", got "${String(b.chapterId)}".`);
  }
  if (!Array.isArray(b.parts) || b.parts.length < minParts) {
    errors.push(`parts must have at least ${minParts} parts, got ${Array.isArray(b.parts) ? b.parts.length : 0}.`);
    return errors;
  }

  b.parts.forEach((part, pIdx) => {
    if (!part.partSlug || !part.partTitle) {
      errors.push(`Part ${pIdx + 1} missing partSlug or partTitle.`);
    }
    if (!Array.isArray(part.beats) || part.beats.length < 4) {
      errors.push(`Part ${pIdx + 1} must have 4–6 beats.`);
      return;
    }
    part.beats.forEach((beat: StoryboardBeat, bIdx: number) => {
      const tag = `Part ${pIdx + 1} beat ${bIdx + 1} (${beat?.id || "?"})`;
      if (!beat || !VALID_KINDS.has(beat.kind)) {
        errors.push(`${tag}: invalid kind "${String(beat?.kind)}".`);
        return;
      }
      if (!beat.narration || typeof beat.narration !== "string") {
        errors.push(`${tag}: missing narration.`);
      } else {
        const wc = beat.narration.trim().split(/\s+/).length;
        if (wc > 52) {
          errors.push(`${tag}: narration too long (${wc} words; keep <= 45 words).`);
        }
        if (/\blive\s+(inside|in|on|within|under|outside)\b/i.test(beat.narration)) {
          errors.push(`${tag}: contains homograph trap "live inside/in/on" — use "sit/belong/reside" instead.`);
        }
      }
      if (beat.sfx && !VALID_SFX.has(beat.sfx)) {
        errors.push(`${tag}: invalid sfx "${beat.sfx}".`);
      }
      if (beat.kind === "code-spotlight") {
        const lines = (beat.code || "").split("\n");
        if (lines.length === 0 || lines.length > 13) {
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
          errors.push(`${tag}: compare must have top and bottom objects with label, badge, tone.`);
        }
      }
      if (beat.kind === "cta") {
        if (!Array.isArray(beat.takeaways) || beat.takeaways.length < 2) {
          errors.push(`${tag}: cta must have 2–4 takeaways.`);
        }
      }
    });
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
      console.log(`[Ollama] ${label} | prefilling prompt on GPU... (${sec}s)`);
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
          temperature: 0.45,
          num_ctx: 6144,
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
          const tokPerSec = (tokenCount / Math.max(0.1, (now - startedAt) / 1000)).toFixed(1);
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
  console.log(
    `[Ollama] ${label} finished (${tokenCount} tokens in ${totalSec}s)`
  );

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

  // Load 1 compact example part from reference storyboard to anchor exact JSON schema
  const refBundle = JSON.parse(
    fs.readFileSync(referenceStoryboardPath, "utf-8")
  ) as ChapterStoryboardBundle;
  const schemaExample = JSON.stringify(
    {
      ...refBundle,
      parts: [refBundle.parts[0]],
    },
    null,
    2
  );

  // Keep prompt compact so qwen2.5:7b + 6144 context stays on RTX 2060 6GB GPU
  const maxMdChars = 9500;
  const mdSnippet =
    markdown.length > maxMdChars
      ? `${markdown.slice(0, maxMdChars)}\n\n[...truncated for context window...]`
      : markdown;

  const basePrompt = `You are a senior Java engineer scripting crisp, engaging 9:16 YouTube Shorts for JavaHub.
Convert the following chapter Markdown into a JSON object matching the EXACT TypeScript schema of the example below.

Target metadata (MUST match exactly):
- chapterId: "${chapterId}"
- level: "${level}"
- levelLabel: "${levelLabel}"
- slug: "${slug}"
- chapterTitle: "${chapterTitle}"
- roadmapUrl: "https://ravishankar2463.github.io/java_hub/roadmap/${chapterId}"
- branchName: "topic/${chapterId}"
- voice: "af_heart"
- speed: 1.24

Part count rule (based on chapter complexity: ${complexity.wordCount} words, ${complexity.h2Count} sections):
- Create EXACTLY ${complexity.recommendedParts} focused, self-contained parts (partNumber 1..${complexity.recommendedParts}).
- Each part has 5 beats:
  1. "hook" (eyebrow, headline, highlightWord, subline, sfx:"pop")
  2-4. Mix of "code-spotlight" (1-12 lines of Java, valid 1-based steps[].lines), "flow-diagram" (3-4 nodes), "terminal" (3-7 lines), "compare" (top vs bottom with tone "bad"|"good"|"neutral")
  5. "cta" (3 crisp takeaways, nextPartTeaser or command: "git checkout topic/${chapterId}", sfx:"success")

Strict TTS & Mobile 9:16 Rules:
- Conversational spoken English (contractions, punchy, senior-dev tone).
- Every beat narration MUST be 16–40 words (NEVER >45 words).
- NEVER use verb "live" (e.g. "live inside" -> use "sit inside", "belong inside", "reside in").
- Every code-spotlight code MUST be <= 12 lines; every steps[].lines entry MUST be 1..lineCount.
- Every partSlug format: "part-<N>-<short-kebab-slug>".
- Every beat id format: "p<N>-b<M>-<kind>".
- Output ONLY valid JSON (no markdown fences, no commentary).

Reference Schema Example:
${schemaExample}

Chapter Markdown to convert:
${mdSnippet}`;

  let lastErrors: string[] = [];
  let lastRaw = "";

  for (let attempt = 1; attempt <= 3; attempt++) {
    const prompt =
      attempt === 1
        ? basePrompt
        : `${basePrompt}\n\nYour previous JSON had these validation errors — fix ALL of them and return ONLY valid JSON:\n- ${lastErrors.join("\n- ")}\n\nPrevious JSON:\n${lastRaw.slice(0, 6000)}`;

    console.log(
      `[Ollama:${model}] Drafting ${chapterId} (${complexity.recommendedParts} parts, attempt ${attempt}/3)...`
    );
    lastRaw = await callOllamaJson(prompt, model, `${chapterId} (attempt ${attempt}/3)`);

    try {
      const parsed = JSON.parse(lastRaw) as ChapterStoryboardBundle;
      // Enforce deterministic metadata fields so small LLM drift never breaks paths
      parsed.chapterId = chapterId;
      parsed.level = level;
      parsed.levelLabel = levelLabel;
      parsed.slug = slug;
      parsed.chapterTitle = chapterTitle;
      parsed.roadmapUrl = `https://ravishankar2463.github.io/java_hub/roadmap/${chapterId}`;
      parsed.branchName = `topic/${chapterId}`;
      parsed.voice = "af_heart";
      parsed.speed = 1.24;
      if (Array.isArray(parsed.parts)) {
        parsed.parts.forEach((p, idx) => {
          p.partNumber = idx + 1;
        });
      }

      lastErrors = validateStoryboardBundle(parsed, chapterId, complexity.recommendedParts);
      if (lastErrors.length === 0) {
        fs.mkdirSync(path.dirname(outStoryboardPath), { recursive: true });
        fs.writeFileSync(outStoryboardPath, JSON.stringify(parsed, null, 2), "utf-8");
        console.log(
          `[Ollama] Validated & saved ${outStoryboardPath} (${parsed.parts.length} parts)`
        );
        return parsed;
      }
      console.warn(
        `[Ollama] Attempt ${attempt} had ${lastErrors.length} validation issue(s): ${lastErrors[0]}`
      );
    } catch (err) {
      lastErrors = [`JSON.parse failed: ${err instanceof Error ? err.message : String(err)}`];
      console.warn(`[Ollama] Attempt ${attempt} invalid JSON: ${lastErrors[0]}`);
    }
  }

  throw new Error(
    `Failed to generate valid storyboard for ${chapterId} after 3 attempts:\n${lastErrors.join("\n")}`
  );
}
