import fs from "node:fs";
import path from "node:path";
import type { ChapterStoryboardBundle, StoryboardBeat } from "../src/types.js";
import { estimateChapterComplexity } from "./ollama-storyboard.js";

const DEFAULT_GEMINI_MODEL = process.env.GEMINI_MODEL || "gemini-2.5-flash";

const VALID_KINDS = new Set([
  "hook",
  "code-spotlight",
  "flow-diagram",
  "terminal",
  "compare",
  "cta",
]);
const VALID_SFX = new Set(["none", "whoosh", "pop", "keyclick", "success", "error"]);

const SLOP_PATTERNS: Array<{ pattern: RegExp; label: string }> = [
  { pattern: /\bhey there\b/i, label: '"Hey there"' },
  { pattern: /\bwelcome to (?:the |our )/i, label: '"Welcome to..."' },
  { pattern: /\bstay tuned\b/i, label: '"Stay tuned"' },
  { pattern: /\beasy peasy\b/i, label: '"Easy peasy"' },
  { pattern: /\bsee you there\b/i, label: '"See you there"' },
  { pattern: /\bthat'?s all for today\b/i, label: '"That\'s all for today"' },
  { pattern: /\blet'?s dive (?:right )?into\b/i, label: '"Let\'s dive into"' },
];

export function loadEnvFile(studioRoot: string): void {
  const envPath = path.join(studioRoot, ".env");
  if (!fs.existsSync(envPath)) return;
  const lines = fs.readFileSync(envPath, "utf-8").split(/\r?\n/);
  for (const line of lines) {
    const trimmed = line.trim();
    if (!trimmed || trimmed.startsWith("#")) continue;
    const eq = trimmed.indexOf("=");
    if (eq === -1) continue;
    const key = trimmed.slice(0, eq).trim();
    let val = trimmed.slice(eq + 1).trim();
    if (
      (val.startsWith('"') && val.endsWith('"')) ||
      (val.startsWith("'") && val.endsWith("'"))
    ) {
      val = val.slice(1, -1);
    }
    if (key && !process.env[key]) {
      process.env[key] = val;
    }
  }
}

export function getGeminiApiKey(studioRoot: string): string {
  loadEnvFile(studioRoot);
  const key = process.env.GEMINI_API_KEY?.trim();
  if (!key || key === "your_gemini_api_key_here") {
    throw new Error(
      `GEMINI_API_KEY is not set.\nPaste your Google AI Studio key into:\n  ${path.join(
        studioRoot,
        ".env"
      )}\nFormat:\n  GEMINI_API_KEY=AIza...`
    );
  }
  return key;
}

function stripBackticks(s: string): string {
  return s.replace(/`([^`]+)`/g, "$1");
}

function sanitizeAndValidateBundle(
  bundle: ChapterStoryboardBundle,
  expectedChapterId: string,
  expectedParts: number
): string[] {
  const errors: string[] = [];
  if (!bundle || !Array.isArray(bundle.parts) || bundle.parts.length !== expectedParts) {
    return [
      `Expected exactly ${expectedParts} parts, got ${
        Array.isArray(bundle?.parts) ? bundle.parts.length : 0
      }.`,
    ];
  }

  bundle.parts.forEach((part, pIdx) => {
    const pNum = pIdx + 1;
    part.partNumber = pNum;
    if (!part.partSlug || !part.partTitle) {
      errors.push(`Part ${pNum}: missing partSlug or partTitle.`);
    }
    if (!Array.isArray(part.beats) || part.beats.length !== 5) {
      errors.push(
        `Part ${pNum}: must have exactly 5 beats, got ${
          Array.isArray(part.beats) ? part.beats.length : 0
        }.`
      );
      return;
    }

    part.beats.forEach((beat: StoryboardBeat, bIdx: number) => {
      const tag = `Part ${pNum} Beat ${bIdx + 1} (${beat?.id || "?"})`;
      if (!beat || !VALID_KINDS.has(beat.kind)) {
        errors.push(`${tag}: invalid kind "${String(beat?.kind)}".`);
        return;
      }
      if (!beat.sfx || !VALID_SFX.has(beat.sfx)) beat.sfx = "whoosh";
      if (typeof beat.durationSec !== "number") beat.durationSec = 11;

      if (!beat.narration || typeof beat.narration !== "string") {
        errors.push(`${tag}: missing narration.`);
      } else {
        beat.narration = stripBackticks(beat.narration).trim();
        const wc = beat.narration.split(/\s+/).length;
        if (wc > 48) {
          errors.push(`${tag}: narration too long (${wc} words; max 44).`);
        }
        if (wc < 10) {
          errors.push(`${tag}: narration too short (${wc} words; min 12).`);
        }
        for (const { pattern, label } of SLOP_PATTERNS) {
          if (pattern.test(beat.narration)) {
            errors.push(`${tag}: AI filler phrase ${label} is forbidden — rewrite crisply.`);
          }
        }
        if (/\blive\s+(inside|in|on|within|under|outside)\b/i.test(beat.narration)) {
          errors.push(
            `${tag}: homograph trap "live inside/in/on" — use "sit", "belong", or "reside".`
          );
        }
      }

      if (beat.kind === "hook") {
        beat.headline = stripBackticks(beat.headline || "");
        beat.subline = stripBackticks(beat.subline || "");
        if (!beat.eyebrow || !beat.headline || !beat.subline) {
          errors.push(`${tag}: hook missing eyebrow/headline/subline.`);
        }
      }

      if (beat.kind === "code-spotlight") {
        const lines = (beat.code || "").split("\n");
        if (!beat.code || lines.length > 12) {
          errors.push(`${tag}: code-spotlight code must be 1–12 lines (got ${lines.length}).`);
        }
        if (/^[^\n]+\n\s*import\s+/m.test(beat.code || "")) {
          errors.push(`${tag}: invalid Java — import statement must appear at the top of the file.`);
        }
        if (!Array.isArray(beat.steps) || beat.steps.length === 0) {
          errors.push(`${tag}: code-spotlight must have 1–3 steps.`);
        } else {
          beat.steps.forEach((st) => {
            st.explanation = stripBackticks(st.explanation || "");
            st.badge = stripBackticks(st.badge || "");
            if (Array.isArray(st.lines)) {
              st.lines = st.lines.map((n) =>
                Math.max(1, Math.min(lines.length, Number(n) || 1))
              );
            }
          });
        }
      }

      if (beat.kind === "flow-diagram") {
        if (!Array.isArray(beat.nodes) || beat.nodes.length < 3 || beat.nodes.length > 5) {
          errors.push(`${tag}: flow-diagram must have 3–5 nodes.`);
        } else {
          beat.nodes.forEach((n, nIdx) => {
            n.label = stripBackticks(n.label || "");
            n.detail = stripBackticks(n.detail || "");
            if (!n.label || !n.detail) {
              errors.push(`${tag}: flow-diagram node ${nIdx + 1} must have non-empty label AND detail.`);
            }
          });
        }
      }

      if (beat.kind === "terminal") {
        if (!Array.isArray(beat.lines) || beat.lines.length < 2 || beat.lines.length > 9) {
          errors.push(`${tag}: terminal must have 2–9 lines.`);
        } else {
          beat.lines.forEach((ln, lIdx) => {
            if (!ln || typeof ln.text !== "string" || !ln.text.trim()) {
              errors.push(`${tag}: terminal line ${lIdx + 1} missing non-empty "text" string.`);
            }
          });
        }
      }

      if (beat.kind === "compare") {
        if (!beat.top?.label || !beat.bottom?.label) {
          errors.push(`${tag}: compare missing top/bottom labels.`);
        }
        if (beat.top?.points) beat.top.points = beat.top.points.map(stripBackticks);
        if (beat.bottom?.points) beat.bottom.points = beat.bottom.points.map(stripBackticks);
      }

      if (beat.kind === "cta") {
        if (!Array.isArray(beat.takeaways) || beat.takeaways.length < 2) {
          errors.push(`${tag}: cta must have 2–3 takeaways.`);
        } else {
          beat.takeaways = beat.takeaways.map(stripBackticks);
        }
      }
    });
  });

  if (bundle.chapterId !== expectedChapterId) {
    bundle.chapterId = expectedChapterId;
  }
  return errors;
}

async function callGeminiJson(
  apiKey: string,
  model: string,
  prompt: string,
  label: string
): Promise<string> {
  const startedAt = Date.now();
  const url = `https://generativelanguage.googleapis.com/v1beta/models/${encodeURIComponent(
    model
  )}:generateContent?key=${encodeURIComponent(apiKey)}`;

  console.log(`[Gemini:${model}] Requesting ${label}...`);
  const res = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      contents: [{ role: "user", parts: [{ text: prompt }] }],
      generationConfig: {
        temperature: 0.45,
        responseMimeType: "application/json",
      },
    }),
  });

  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Gemini API error (${res.status}): ${errText.slice(0, 400)}`);
  }

  const payload = (await res.json()) as {
    candidates?: Array<{ content?: { parts?: Array<{ text?: string }> } }>;
  };
  const text = payload.candidates?.[0]?.content?.parts?.[0]?.text;
  const elapsed = ((Date.now() - startedAt) / 1000).toFixed(1);
  console.log(`[Gemini:${model}] Received ${label} in ${elapsed}s`);

  if (!text) {
    throw new Error("Gemini returned empty response.");
  }
  return text;
}

export async function generateStoryboardWithGemini(params: {
  studioRoot: string;
  hubDir: string;
  chapterId: string;
  referenceStoryboardPath: string;
  outStoryboardPath: string;
  model?: string;
}): Promise<ChapterStoryboardBundle> {
  const { studioRoot, hubDir, chapterId, referenceStoryboardPath, outStoryboardPath } = params;
  const apiKey = getGeminiApiKey(studioRoot);
  const model = params.model || DEFAULT_GEMINI_MODEL;

  const [level, slug] = chapterId.split("/");
  const levelNum = level.replace(/\D/g, "");
  const levelLabel = `Level ${levelNum}`;

  const mdPath = path.join(hubDir, "content", "roadmap", level, `${slug}.md`);
  const markdown = fs.readFileSync(mdPath, "utf-8");
  const complexity = estimateChapterComplexity(markdown);
  const titleMatch = markdown.match(/^title:\s*["']?(.+?)["']?\s*$/m);
  const chapterTitle = titleMatch ? titleMatch[1].trim() : slug.replace(/-/g, " ");

  const refJson = fs.readFileSync(referenceStoryboardPath, "utf-8");

  const basePrompt = `You are a Principal Java Engineer & technical YouTube creator (Fireship + ByteByteGo style).
Convert the full JavaHub chapter Markdown below into a crisp, high-signal, human-sounding ${complexity.recommendedParts}-part 9:16 YouTube Shorts storyboard JSON.

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

Structure & Quality Bar:
- Create EXACTLY ${complexity.recommendedParts} distinct, non-overlapping parts covering ALL major concepts, internals, pitfalls, and modern Java 21–25 nuances from the chapter.
- Each part has EXACTLY 5 beats:
  1. "hook" (punchy curiosity question or bold claim, sfx:"pop")
  2-4. High-signal mix of "code-spotlight" (valid Java, <=12 lines, 1-based steps[].lines), "flow-diagram" (3-4 nodes, non-empty label AND detail), "terminal" (real CLI/javap/output lines with exact "kind" and "text" keys), "compare" (top vs bottom with tone "bad"|"good"|"neutral").
  5. "cta" (3 concrete technical takeaways; last part includes command: "git checkout topic/${chapterId}" and roadmap URL teaser; earlier parts tease the next part).
- ZERO AI filler! Strictly FORBIDDEN: "Hey there", "Welcome to", "Stay tuned", "Easy peasy", "See you there", "That's all for today", "Let's dive into". Start every hook with a sharp technical observation or bug!
- Write for the ear: contractions, short punchy sentences, 18–40 words per beat (max 44 words).
- NEVER use raw markdown backticks (\`) in narration, explanations, labels, or takeaways.
- NEVER use verb "live" ("live inside" -> use "sit inside", "belong inside", "reside in").
- Output ONLY valid JSON matching the exact schema of the gold-standard reference below.

Gold-Standard Reference Storyboard (match this exact schema and human senior-dev tone):
${refJson}

Source Chapter Markdown (${complexity.wordCount} words):
${markdown}`;

  let lastErrors: string[] = [];
  let lastRaw = "";

  for (let attempt = 1; attempt <= 3; attempt++) {
    const prompt =
      attempt === 1
        ? basePrompt
        : `${basePrompt}\n\nFix ALL of these validation issues from your previous JSON and return ONLY valid JSON:\n- ${lastErrors.join(
            "\n- "
          )}\n\nPrevious JSON:\n${lastRaw}`;

    lastRaw = await callGeminiJson(
      apiKey,
      model,
      prompt,
      `${chapterId} (${complexity.recommendedParts} parts, try ${attempt}/3)`
    );

    try {
      const parsed = JSON.parse(lastRaw) as ChapterStoryboardBundle;
      parsed.chapterId = chapterId;
      parsed.level = level;
      parsed.levelLabel = levelLabel;
      parsed.slug = slug;
      parsed.chapterTitle = chapterTitle;
      parsed.roadmapUrl = `https://ravishankar2463.github.io/java_hub/roadmap/${chapterId}`;
      parsed.branchName = `topic/${chapterId}`;
      parsed.voice = "af_heart";
      parsed.speed = 1.24;

      lastErrors = sanitizeAndValidateBundle(
        parsed,
        chapterId,
        complexity.recommendedParts
      );
      if (lastErrors.length === 0) {
        fs.mkdirSync(path.dirname(outStoryboardPath), { recursive: true });
        fs.writeFileSync(outStoryboardPath, JSON.stringify(parsed, null, 2), "utf-8");
        console.log(
          `[Gemini] Validated & saved ${outStoryboardPath} (${parsed.parts.length} parts)`
        );
        return parsed;
      }
      console.warn(
        `[Gemini] Attempt ${attempt} had ${lastErrors.length} issue(s): ${lastErrors[0]}`
      );
    } catch (err) {
      lastErrors = [`JSON.parse failed: ${err instanceof Error ? err.message : String(err)}`];
      console.warn(`[Gemini] Attempt ${attempt} invalid JSON: ${lastErrors[0]}`);
    }
  }

  throw new Error(
    `Gemini failed validation for ${chapterId} after 3 attempts:\n${lastErrors.join("\n")}`
  );
}
