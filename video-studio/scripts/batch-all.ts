import fs from "node:fs";
import path from "node:path";
import { execFileSync, spawn } from "node:child_process";
import { fileURLToPath } from "node:url";
import { listChapters } from "./parse-chapter.js";
import {
  checkOllamaReady,
  generateStoryboardWithOllama,
} from "./ollama-storyboard.js";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const STUDIO_ROOT = path.resolve(__dirname, "..");
const REPO_ROOT = path.resolve(STUDIO_ROOT, "..");
const DEFAULT_HUB_DIR =
  process.env.JAVA_HUB_DIR || path.resolve(REPO_ROOT, "..", "java_hub");
const STORYBOARDS_DIR = path.join(STUDIO_ROOT, "storyboards");
const MANIFEST_PATH = path.join(REPO_ROOT, "videos", "manifest.json");
const REF_STORYBOARD = path.join(
  STORYBOARDS_DIR,
  "level-0",
  "anatomy-of-a-java-program.json"
);

interface BatchOptions {
  startLevel: number;
  endLevel: number;
  model: string;
  hubDir: string;
  noPush: boolean;
  limit?: number;
}

function parseArgs(argv: string[]): BatchOptions {
  const opts: BatchOptions = {
    startLevel: 0,
    endLevel: 15,
    model: process.env.OLLAMA_MODEL || "qwen2.5:7b",
    hubDir: DEFAULT_HUB_DIR,
    noPush: false,
  };
  for (let i = 0; i < argv.length; i++) {
    const a = argv[i];
    const next = argv[i + 1];
    switch (a) {
      case "--start-level":
        opts.startLevel = Number(next);
        i++;
        break;
      case "--end-level":
        opts.endLevel = Number(next);
        i++;
        break;
      case "--model":
        opts.model = next;
        i++;
        break;
      case "--hub-dir":
        opts.hubDir = path.resolve(next);
        i++;
        break;
      case "--limit":
        opts.limit = Number(next);
        i++;
        break;
      case "--no-push":
        opts.noPush = true;
        break;
    }
  }
  return opts;
}

function getCompletedChapterIds(): Set<string> {
  if (!fs.existsSync(MANIFEST_PATH)) return new Set();
  try {
    const parsed = JSON.parse(fs.readFileSync(MANIFEST_PATH, "utf-8"));
    const ids = new Set<string>();
    for (const v of parsed.videos || []) {
      if (v.chapterId && v.releaseTag) ids.add(v.chapterId);
    }
    return ids;
  } catch {
    return new Set();
  }
}

function runSync(cmd: string, args: string[], cwd: string): void {
  execFileSync(cmd, args, { cwd, stdio: "inherit", shell: process.platform === "win32" });
}

function runAsync(cmd: string, args: string[], cwd: string): Promise<void> {
  return new Promise((resolve, reject) => {
    const child = spawn(cmd, args, {
      cwd,
      stdio: "inherit",
      shell: process.platform === "win32",
    });
    child.on("error", reject);
    child.on("close", (code) => {
      if (code === 0) resolve();
      else reject(new Error(`Command failed (${code}): ${cmd} ${args.join(" ")}`));
    });
  });
}

function commitAndPushTopic(chapterId: string, hubDir: string, noPush: boolean): void {
  console.log(`\n[Git] Committing & syncing ${chapterId}...`);

  // 1. Update VIDEO_PROGRESS.md in java_hub
  runSync(
    "node",
    ["--experimental-strip-types", "scripts/update-video-progress.mjs"],
    hubDir
  );

  // 2. Commit & push java_hub_resources (main) — ONLY storyboard JSON + videos/manifest.json
  const sbRel = `video-studio/storyboards/${chapterId}.json`;
  runSync("git", ["add", sbRel, "videos/manifest.json"], REPO_ROOT);
  const resStatus = execFileSync("git", ["diff", "--cached", "--name-only"], {
    cwd: REPO_ROOT,
    encoding: "utf-8",
  }).trim();
  if (resStatus) {
    runSync(
      "git",
      ["commit", "-m", `feat(videos): add ${chapterId} Shorts storyboard and release manifest`],
      REPO_ROOT
    );
    if (!noPush) {
      runSync("git", ["push", "origin", "main"], REPO_ROOT);
    }
  }

  // 3. Commit & push java_hub (current tracking PR branch, e.g. feat/chapter-summary-videos)
  runSync("git", ["add", "data/chapter-videos.json", "VIDEO_PROGRESS.md"], hubDir);
  const hubStatus = execFileSync("git", ["diff", "--cached", "--name-only"], {
    cwd: hubDir,
    encoding: "utf-8",
  }).trim();
  if (hubStatus) {
    runSync(
      "git",
      ["commit", "-m", `feat(videos): add ${chapterId} Shorts to website and progress tracker`],
      hubDir
    );
    if (!noPush) {
      const branch = execFileSync("git", ["branch", "--show-current"], {
        cwd: hubDir,
        encoding: "utf-8",
      }).trim();
      runSync("git", ["push", "origin", branch], hubDir);
    }
  }
}

async function ensureStoryboard(
  chapterId: string,
  hubDir: string,
  model: string
): Promise<string> {
  const sbPath = path.join(STORYBOARDS_DIR, `${chapterId}.json`);
  if (fs.existsSync(sbPath)) {
    console.log(`[Storyboard] Using existing ${sbPath}`);
    return sbPath;
  }
  await generateStoryboardWithOllama({
    hubDir,
    chapterId,
    referenceStoryboardPath: REF_STORYBOARD,
    outStoryboardPath: sbPath,
    model,
  });
  return sbPath;
}

/**
 * Hardware-tuned 2-lane pipeline for 6-core / 16 GB RAM / RTX 2060 6 GB:
 * - GPU Lane (Ollama qwen2.5:7b) pre-generates & validates Storyboard N+1
 * - CPU Lane (Kokoro fp32 + Remotion H.264 + gh release upload) renders Chapter N
 * - Sequential Git commits keep both repos + PR #6 atomic and conflict-free.
 */
async function main(): Promise<void> {
  const opts = parseArgs(process.argv.slice(2));
  const completed = getCompletedChapterIds();
  const allChapters = listChapters(opts.hubDir).filter((id) => {
    const lvl = Number(id.split("/")[0].replace(/\D/g, ""));
    return lvl >= opts.startLevel && lvl <= opts.endLevel && !completed.has(id);
  });

  const queue = opts.limit ? allChapters.slice(0, opts.limit) : allChapters;
  console.log(
    `\n=== JavaHub Zero-Credit Local Batch Runner ===\n` +
      `Levels: ${opts.startLevel}..${opts.endLevel} | Pending: ${queue.length} chapters | Ollama model: ${opts.model}\n`
  );

  if (queue.length === 0) {
    console.log("All matching chapters are already published!");
    return;
  }

  // Only require Ollama if at least one queued chapter lacks a storyboard
  const needsOllama = queue.some(
    (id) => !fs.existsSync(path.join(STORYBOARDS_DIR, `${id}.json`))
  );
  if (needsOllama) {
    await checkOllamaReady(opts.model);
  }

  let nextStoryboardPromise: Promise<string> | null = ensureStoryboard(
    queue[0],
    opts.hubDir,
    opts.model
  );

  let succeeded = 0;
  const failed: Array<{ chapterId: string; error: string }> = [];

  for (let i = 0; i < queue.length; i++) {
    const chapterId = queue[i];
    const nextChapterId = queue[i + 1];

    console.log(`\n============================================================`);
    console.log(`[${i + 1}/${queue.length}] Starting ${chapterId}`);
    console.log(`============================================================`);

    try {
      // Wait for current chapter's storyboard from GPU lane
      await nextStoryboardPromise;

      // Kick off GPU lane for Chapter N+1 IN PARALLEL with CPU render of Chapter N!
      if (nextChapterId) {
        nextStoryboardPromise = ensureStoryboard(
          nextChapterId,
          opts.hubDir,
          opts.model
        ).catch((err) => {
          console.error(`[Prefetch Error] ${nextChapterId}:`, err);
          throw err;
        });
      } else {
        nextStoryboardPromise = null;
      }

      // CPU Lane: Kokoro fp32 + Remotion 9:16 H.264 + gh release upload + manifest sync
      await runAsync(
        "npm",
        ["run", "generate", "--", "--chapter", chapterId, "--publish-release"],
        STUDIO_ROOT
      );

      // Atomic per-topic commit & push in both repos
      commitAndPushTopic(chapterId, opts.hubDir, opts.noPush);
      succeeded++;
      console.log(`[Done ${i + 1}/${queue.length}] ${chapterId} published & pushed!`);
    } catch (err) {
      const msg = err instanceof Error ? err.message : String(err);
      console.error(`[FAILED] ${chapterId}: ${msg}`);
      failed.push({ chapterId, error: msg });
      // Ensure next chapter still has a storyboard promise if loop continues
      if (nextChapterId && !nextStoryboardPromise) {
        nextStoryboardPromise = ensureStoryboard(nextChapterId, opts.hubDir, opts.model);
      }
    }
  }

  console.log(`\n=== Batch Complete: ${succeeded}/${queue.length} succeeded ===`);
  if (failed.length > 0) {
    console.log("Failed chapters (re-run batch-all to retry):");
    failed.forEach((f) => console.log(`  - ${f.chapterId}: ${f.error}`));
    process.exitCode = 1;
  }
}

main().catch((err) => {
  console.error("\n[Fatal]", err instanceof Error ? err.message : err);
  process.exit(1);
});
