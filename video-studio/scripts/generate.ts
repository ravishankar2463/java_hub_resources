import { execFileSync } from "node:child_process";
import fs from "node:fs";
import os from "node:os";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { bundle } from "@remotion/bundler";
import { renderMedia, selectComposition } from "@remotion/renderer";
import { listChapters } from "./parse-chapter.js";
import { ensureProceduralSfx } from "./sfx.js";
import { synthesizePartAudio } from "./tts.js";
import type {
  ChapterStoryboardBundle,
  PartStoryboard,
  VideoManifestEntry,
} from "../src/types.js";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const STUDIO_ROOT = path.resolve(__dirname, "..");
const REPO_ROOT = path.resolve(STUDIO_ROOT, "..");
const DEFAULT_HUB_DIR =
  process.env.JAVA_HUB_DIR || path.resolve(REPO_ROOT, "..", "java_hub");
const DEFAULT_OUT_DIR = path.join(REPO_ROOT, "videos");
const STORYBOARDS_DIR = path.join(STUDIO_ROOT, "storyboards");
const PUBLIC_DIR = path.join(STUDIO_ROOT, "public");
const RELEASE_REPO = "ravishankar2463/java_hub_resources";

interface CliOptions {
  chapter?: string;
  partNumber?: number;
  voice?: string;
  speed?: number;
  hubDir: string;
  outDir: string;
  skipTts: boolean;
  publishRelease: boolean;
  storyboardPath?: string;
  list: boolean;
}

function parseArgs(argv: string[]): CliOptions {
  const opts: CliOptions = {
    hubDir: DEFAULT_HUB_DIR,
    outDir: DEFAULT_OUT_DIR,
    skipTts: false,
    publishRelease: false,
    list: false,
  };

  for (let i = 0; i < argv.length; i++) {
    const arg = argv[i];
    const next = argv[i + 1];
    switch (arg) {
      case "--chapter":
      case "-c":
        opts.chapter = next;
        i++;
        break;
      case "--part":
      case "-p":
        opts.partNumber = Number(next);
        i++;
        break;
      case "--voice":
      case "-v":
        opts.voice = next;
        i++;
        break;
      case "--speed":
        opts.speed = Number(next);
        i++;
        break;
      case "--hub-dir":
        opts.hubDir = path.resolve(next);
        i++;
        break;
      case "--out-dir":
        opts.outDir = path.resolve(next);
        i++;
        break;
      case "--storyboard":
        opts.storyboardPath = path.resolve(next);
        i++;
        break;
      case "--skip-tts":
        opts.skipTts = true;
        break;
      case "--publish-release":
        opts.publishRelease = true;
        break;
      case "--list":
        opts.list = true;
        break;
      case "--help":
      case "-h":
        printHelp();
        process.exit(0);
    }
  }
  return opts;
}

function printHelp(): void {
  console.log(`
JavaHub Shorts Video Studio (9:16 Vertical • Kokoro-82M + Procedural SFX + Remotion)

Usage:
  npm run generate -- --chapter level-0/anatomy-of-a-java-program
  npm run generate -- --chapter level-0/anatomy-of-a-java-program --part 1
  npm run generate -- --storyboard storyboards/level-0/anatomy-of-a-java-program.json
  npm run list

Options:
  -c, --chapter <level-N/slug>   Render all parts for a chapter storyboard
  -p, --part <number>            Render only a specific part number (e.g. 1)
  -v, --voice <kokoro-voice>     Override Kokoro voice (default: am_michael)
      --speed <number>           Override speech speed (default: 1.1)
      --storyboard <path>        Explicit path to a chapter storyboard JSON
      --out-dir <path>           Output directory (default: ../videos)
      --skip-tts                 Skip audio generation (silent fast preview)
      --publish-release          Create/reuse videos-<level> GitHub Release and upload MP4s
      --list                     List chapters in java_hub and authored storyboards
`);
}

function resolveBundlePath(opts: CliOptions): string {
  if (opts.storyboardPath) return opts.storyboardPath;
  if (!opts.chapter) {
    printHelp();
    process.exit(1);
  }
  const normalized = opts.chapter.replace(/\\/g, "/").replace(/\.json$/i, "");
  const candidate = path.join(STORYBOARDS_DIR, `${normalized}.json`);
  if (!fs.existsSync(candidate)) {
    throw new Error(
      `Storyboard not found at ${candidate}.\nCreate a rich multi-part storyboard JSON there first (see storyboards/level-0/anatomy-of-a-java-program.json as reference).`
    );
  }
  return candidate;
}

function expandBundleToParts(bundleData: ChapterStoryboardBundle): PartStoryboard[] {
  const totalParts = bundleData.parts.length;
  return bundleData.parts.map((p) => ({
    partNumber: p.partNumber,
    totalParts,
    partSlug: p.partSlug,
    partTitle: p.partTitle,
    chapterId: bundleData.chapterId,
    level: bundleData.level,
    levelLabel: bundleData.levelLabel,
    slug: bundleData.slug,
    chapterTitle: bundleData.chapterTitle,
    roadmapUrl: bundleData.roadmapUrl,
    branchName: bundleData.branchName,
    fps: 30,
    voice: bundleData.voice,
    speed: bundleData.speed,
    beats: p.beats,
  }));
}

function ensureGitHubRelease(level: string, ensured: Set<string>): string {
  const releaseTag = `videos-${level}`;
  if (ensured.has(releaseTag)) return releaseTag;

  try {
    execFileSync(
      "gh",
      ["release", "view", releaseTag, "--repo", RELEASE_REPO],
      { stdio: "ignore" }
    );
  } catch {
    const levelNum = level.replace(/^level-/i, "");
    console.log(`[Release] Creating GitHub Release ${releaseTag} on ${RELEASE_REPO}...`);
    execFileSync(
      "gh",
      [
        "release",
        "create",
        releaseTag,
        "--repo",
        RELEASE_REPO,
        "--title",
        `JavaHub Shorts — Level ${levelNum}`,
        "--notes",
        `9:16 short summary videos for Level ${levelNum}.`,
      ],
      { stdio: "inherit" }
    );
  }

  ensured.add(releaseTag);
  return releaseTag;
}

function uploadReleaseAsset(releaseTag: string, localFile: string, assetName: string): void {
  console.log(`[Release] Uploading ${assetName} -> ${releaseTag}...`);
  const tmpDir = fs.mkdtempSync(path.join(os.tmpdir(), "javahub-release-"));
  const stagedFile = path.join(tmpDir, assetName);
  try {
    fs.copyFileSync(localFile, stagedFile);
    execFileSync(
      "gh",
      [
        "release",
        "upload",
        releaseTag,
        stagedFile,
        "--repo",
        RELEASE_REPO,
        "--clobber",
      ],
      { stdio: "inherit" }
    );
  } finally {
    fs.rmSync(tmpDir, { recursive: true, force: true });
  }
}

function updateManifest(outDir: string, newEntries: VideoManifestEntry[]): string {
  const manifestPath = path.join(outDir, "manifest.json");
  let existing: VideoManifestEntry[] = [];
  if (fs.existsSync(manifestPath)) {
    try {
      const parsed = JSON.parse(fs.readFileSync(manifestPath, "utf-8"));
      if (Array.isArray(parsed.videos)) existing = parsed.videos;
    } catch {
      existing = [];
    }
  }

  const byKey = new Map<string, VideoManifestEntry>();
  for (const e of existing) {
    byKey.set(`${e.chapterId}::${e.partNumber}::${e.format}`, e);
  }
  for (const e of newEntries) {
    const key = `${e.chapterId}::${e.partNumber}::${e.format}`;
    const prev = byKey.get(key);
    byKey.set(key, {
      ...e,
      ...(prev?.youtubeId ? { youtubeId: prev.youtubeId } : {}),
      ...(prev?.youtubeUrl ? { youtubeUrl: prev.youtubeUrl } : {}),
      ...(prev?.youtubeStatus ? { youtubeStatus: prev.youtubeStatus } : {}),
      ...(prev?.scheduledPublishAt ? { scheduledPublishAt: prev.scheduledPublishAt } : {}),
      ...(prev?.publishedAt ? { publishedAt: prev.publishedAt } : {}),
    });
  }

  const sorted = Array.from(byKey.values()).sort((a, b) => {
    const lvlA = Number(a.level.replace(/\D/g, ""));
    const lvlB = Number(b.level.replace(/\D/g, ""));
    if (lvlA !== lvlB) return lvlA - lvlB;
    if (a.slug !== b.slug) return a.slug.localeCompare(b.slug);
    return a.partNumber - b.partNumber;
  });

  const payload = {
    updatedAt: new Date().toISOString(),
    totalVideos: sorted.length,
    videos: sorted,
  };
  fs.writeFileSync(manifestPath, JSON.stringify(payload, null, 2), "utf-8");
  return manifestPath;
}

async function main(): Promise<void> {
  const opts = parseArgs(process.argv.slice(2));

  if (opts.list) {
    const chapters = listChapters(opts.hubDir);
    console.log(`Found ${chapters.length} chapters in ${opts.hubDir}:\n`);
    for (const c of chapters) {
      const hasSb = fs.existsSync(path.join(STORYBOARDS_DIR, `${c}.json`));
      console.log(`  ${hasSb ? "[STORYBOARD]" : "[          ]"} ${c}`);
    }
    return;
  }

  const bundlePath = resolveBundlePath(opts);
  const bundleData = JSON.parse(
    fs.readFileSync(bundlePath, "utf-8")
  ) as ChapterStoryboardBundle;

  let parts = expandBundleToParts(bundleData);
  if (opts.partNumber !== undefined) {
    parts = parts.filter((p) => p.partNumber === opts.partNumber);
    if (parts.length === 0) {
      throw new Error(`Part ${opts.partNumber} not found in ${bundlePath}`);
    }
  }

  fs.mkdirSync(PUBLIC_DIR, { recursive: true });
  ensureProceduralSfx(PUBLIC_DIR);

  // 1. Generate local Kokoro TTS (or pick up .human.wav overrides)
  const preparedParts: PartStoryboard[] = [];
  for (const part of parts) {
    console.log(
      `\n=== Preparing ${part.chapterId} • Part ${part.partNumber}/${part.totalParts}: "${part.partTitle}" ===`
    );
    const withAudio = opts.skipTts
      ? part
      : await synthesizePartAudio(
          part,
          PUBLIC_DIR,
          opts.voice ?? part.voice ?? "af_heart",
          opts.speed ?? part.speed ?? 1.24
        );
    preparedParts.push(withAudio);
  }

  // 2. Bundle Remotion project once
  console.log("\n[Bundle] Bundling Remotion video studio...");
  const serveUrl = await bundle({
    entryPoint: path.join(STUDIO_ROOT, "src", "index.ts"),
    publicDir: PUBLIC_DIR,
  });

  // 3. Render each 9:16 Short
  const manifestEntries: VideoManifestEntry[] = [];
  const ensuredReleases = new Set<string>();
  for (const part of preparedParts) {
    const inputProps = { part, format: "shorts" as const };
    const composition = await selectComposition({
      serveUrl,
      id: "JavaHubShorts",
      inputProps,
    });

    const chapterOutDir = path.join(opts.outDir, part.level, part.slug);
    fs.mkdirSync(chapterOutDir, { recursive: true });
    const outFile = path.join(chapterOutDir, `${part.partSlug}.mp4`);
    const durationSec = Number((composition.durationInFrames / composition.fps).toFixed(1));

    console.log(
      `[Render] Part ${part.partNumber}/${part.totalParts} (${composition.width}x${composition.height}, ${durationSec}s) -> ${outFile}`
    );

    let lastPct = -1;
    const renderStart = Date.now();
    await renderMedia({
      composition,
      serveUrl,
      codec: "h264",
      audioCodec: "aac",
      pixelFormat: "yuv420p",
      crf: 23,
      jpegQuality: 85,
      concurrency: 6,
      outputLocation: outFile,
      inputProps,
      onProgress: ({ progress }) => {
        const pct = Math.floor(progress * 100);
        if (pct >= lastPct + 10 || pct === 100) {
          lastPct = pct;
          const elapsed = ((Date.now() - renderStart) / 1000).toFixed(0);
          console.log(`[Render]   Part ${part.partNumber}/${part.totalParts} — ${pct}% (${elapsed}s)`);
        }
      },
    });

    const relFile = path.relative(opts.outDir, outFile).replace(/\\/g, "/");
    const releaseTag = `videos-${part.level}`;
    const assetName = `${part.level}__${part.slug}__${part.partSlug}.mp4`;
    const videoUrl = `https://github.com/${RELEASE_REPO}/releases/download/${releaseTag}/${assetName}`;

    if (opts.publishRelease) {
      ensureGitHubRelease(part.level, ensuredReleases);
      uploadReleaseAsset(releaseTag, outFile, assetName);
    }

    manifestEntries.push({
      chapterId: part.chapterId,
      level: part.level,
      slug: part.slug,
      chapterTitle: part.chapterTitle,
      partNumber: part.partNumber,
      totalParts: part.totalParts,
      partSlug: part.partSlug,
      partTitle: part.partTitle,
      format: "shorts",
      durationSec,
      file: relFile,
      videoUrl,
      ...(opts.publishRelease ? { releaseTag } : {}),
      roadmapUrl: part.roadmapUrl,
      branchName: part.branchName,
      renderedAt: new Date().toISOString(),
    });
  }

  const manifestPath = updateManifest(opts.outDir, manifestEntries);
  console.log(`\n[Manifest] Updated -> ${manifestPath}`);

  // Also sync manifest into companion java_hub/data/chapter-videos.json when present
  const hubVideosPath = path.join(opts.hubDir, "data", "chapter-videos.json");
  if (fs.existsSync(path.dirname(hubVideosPath))) {
    fs.copyFileSync(manifestPath, hubVideosPath);
    console.log(`[Sync] Synced website manifest -> ${hubVideosPath}`);
  }

  console.log("\n=== Done! Rendered Shorts ===");
  manifestEntries.forEach((m) =>
    console.log(`  - videos/${m.file} (${m.durationSec}s)`)
  );
}

main().catch((err) => {
  console.error("\n[Error]", err instanceof Error ? err.message : err);
  process.exit(1);
});
