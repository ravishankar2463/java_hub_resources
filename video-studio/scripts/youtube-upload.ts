import fs from "node:fs";
import http from "node:http";
import os from "node:os";
import path from "node:path";
import { fileURLToPath } from "node:url";
import type { VideoManifestEntry } from "../src/types.js";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const STUDIO_ROOT = path.resolve(__dirname, "..");
const REPO_ROOT = path.resolve(STUDIO_ROOT, "..");
const DEFAULT_HUB_DIR =
  process.env.JAVA_HUB_DIR || path.resolve(REPO_ROOT, "..", "java_hub");
const DEFAULT_VIDEOS_DIR = path.join(REPO_ROOT, "videos");

const DEFAULT_CHANNEL_ID =
  process.env.YOUTUBE_CHANNEL_ID || "UC7g7lXKsuWp6db3LYM29XPg";
const CLIENT_SECRET_PATH =
  process.env.YOUTUBE_CLIENT_SECRET_PATH ||
  path.join(STUDIO_ROOT, ".youtube-client-secret.json");
const TOKEN_PATH =
  process.env.YOUTUBE_TOKEN_PATH ||
  path.join(STUDIO_ROOT, ".youtube-token.json");

const OAUTH_PORT = 42813;
const OAUTH_REDIRECT_URI = `http://127.0.0.1:${OAUTH_PORT}/oauth2callback`;
const OAUTH_SCOPES = [
  "https://www.googleapis.com/auth/youtube.upload",
  "https://www.googleapis.com/auth/youtube.readonly",
  "https://www.googleapis.com/auth/youtube.force-ssl",
];

type Command =
  | "auth"
  | "upload"
  | "publish-due"
  | "status"
  | "set-id"
  | "stage"
  | "sync-channel"
  | "reschedule";

interface CliOptions {
  command: Command;
  hubDir: string;
  videosDir: string;
  stageDir: string;
  channelId: string;
  limit: number;
  intervalDays: number;
  startDate?: string;
  publishTimeUtc: string;
  chapter?: string;
  partNumber?: number;
  youtubeId?: string;
  status: "unlisted" | "public" | "private";
  updateMetadata: boolean;
  dryRun: boolean;
}

interface OAuthClientConfig {
  client_id: string;
  client_secret: string;
  auth_uri?: string;
  token_uri?: string;
}

interface StoredToken {
  access_token: string;
  refresh_token?: string;
  expires_at: number;
  token_type: string;
  scope?: string;
}

interface ManifestPayload {
  updatedAt: string;
  totalVideos: number;
  videos: VideoManifestEntry[];
}

function slugify(text: string): string {
  return text
    .toString()
    .toLowerCase()
    .trim()
    .replace(/\s+/g, "-")
    .replace(/[^\w-]+/g, "")
    .replace(/--+/g, "-")
    .replace(/^-+/, "")
    .replace(/-+$/, "");
}

function parseArgs(argv: string[]): CliOptions {
  const first = argv[0];
  const validCommands: Command[] = [
    "auth",
    "upload",
    "publish-due",
    "status",
    "set-id",
    "stage",
    "sync-channel",
    "reschedule",
  ];
  const command: Command = validCommands.includes(first as Command)
    ? (first as Command)
    : "status";
  const startIdx = validCommands.includes(first as Command) ? 1 : 0;

  const opts: CliOptions = {
    command,
    hubDir: DEFAULT_HUB_DIR,
    videosDir: DEFAULT_VIDEOS_DIR,
    stageDir: path.join(STUDIO_ROOT, "out", "youtube-batch"),
    channelId: DEFAULT_CHANNEL_ID,
    limit: command === "upload" ? 6 : command === "stage" ? 15 : 50,
    intervalDays: 1,
    publishTimeUtc: "14:00",
    status: "unlisted",
    updateMetadata: true,
    dryRun: false,
  };

  for (let i = startIdx; i < argv.length; i++) {
    const arg = argv[i];
    const next = argv[i + 1];
    switch (arg) {
      case "--hub-dir":
        opts.hubDir = path.resolve(next);
        i++;
        break;
      case "--videos-dir":
        opts.videosDir = path.resolve(next);
        i++;
        break;
      case "--channel-id":
        opts.channelId = next;
        i++;
        break;
      case "--limit":
      case "-n":
        opts.limit = Math.max(1, Number(next));
        i++;
        break;
      case "--interval-days":
        opts.intervalDays = Math.max(1, Number(next));
        i++;
        break;
      case "--start-date":
        opts.startDate = next;
        i++;
        break;
      case "--publish-time":
        opts.publishTimeUtc = next;
        i++;
        break;
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
      case "--id":
        opts.youtubeId = next;
        i++;
        break;
      case "--status":
        if (next === "unlisted" || next === "public" || next === "private") {
          opts.status = next;
        }
        i++;
        break;
      case "--stage-dir":
        opts.stageDir = path.resolve(next);
        i++;
        break;
      case "--no-metadata-update":
        opts.updateMetadata = false;
        break;
      case "--dry-run":
        opts.dryRun = true;
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
  console.log(`JavaHub YouTube Shorts Uploader & Every-Other-Day Publisher
Channel Guard: ${DEFAULT_CHANNEL_ID}

Commands:
  auth          Authenticate with Google OAuth2 and save token locally
  status        Show upload/publish progress and upcoming sequential schedule
  stage         Copy next N un-uploaded MP4s into out/youtube-batch/ for YouTube Studio drag-and-drop
  sync-channel  Scan your YouTube channel, match Studio uploads, fill Shorts metadata & sync manifests
  upload        Upload next un-uploaded Shorts via API as UNLISTED in exact roadmap order
  publish-due   Flip uploaded UNLISTED Shorts whose scheduledPublishAt <= now to PUBLIC
  set-id        Manually bind a YouTube ID/URL to a chapter part and sync manifests

Options:
  --limit, -n <N>          Max videos to process (default: 15 for stage, 6 for upload)
  --interval-days <N>      Days between public releases (default: 1 = daily)
  --start-date YYYY-MM-DD  First public release date when no prior schedule exists
  --publish-time HH:MM     UTC publish time (default: 14:00)
  --chapter, -c <id>       Filter to one chapter (e.g. level-0/environment-setup)
  --part, -p <N>           Filter to one part number
  --id <yt-id-or-url>      YouTube video ID or URL (for set-id)
  --status <state>         unlisted | public | private (for set-id, default: unlisted)
  --no-metadata-update     For sync-channel: only link IDs without calling videos.update
  --dry-run                Preview actions without calling YouTube write APIs
`);
}

/**
 * Reads java_hub/data/roadmap.ts to extract the exact curriculum chapter order
 * (e.g. level-0/environment-setup -> 0, level-0/git-version-control -> 1, ...).
 */
function loadRoadmapChapterOrder(hubDir: string): Map<string, number> {
  const roadmapPath = path.join(hubDir, "data", "roadmap.ts");
  const order = new Map<string, number>();
  if (!fs.existsSync(roadmapPath)) return order;

  const src = fs.readFileSync(roadmapPath, "utf-8");
  const lines = src.split(/\r?\n/);
  let currentLevel: number | null = null;
  let inTopics = false;
  let idx = 0;

  for (const line of lines) {
    const levelMatch = line.match(/^\s*level:\s*(\d+)\s*,/);
    if (levelMatch) {
      currentLevel = Number(levelMatch[1]);
      inTopics = false;
      continue;
    }
    if (/^\s*topics:\s*\[/.test(line)) {
      inTopics = true;
      continue;
    }
    if (inTopics && currentLevel !== null) {
      const titleMatch = line.match(/^\s*title:\s*"([^"]+)"/);
      if (titleMatch) {
        const chapterId = `level-${currentLevel}/${slugify(titleMatch[1])}`;
        if (!order.has(chapterId)) {
          order.set(chapterId, idx++);
        }
      }
    }
  }

  return order;
}

function sortVideosInRoadmapSequence(
  videos: VideoManifestEntry[],
  chapterOrder: Map<string, number>
): VideoManifestEntry[] {
  return [...videos].sort((a, b) => {
    const ordA = chapterOrder.get(a.chapterId) ?? Number.MAX_SAFE_INTEGER;
    const ordB = chapterOrder.get(b.chapterId) ?? Number.MAX_SAFE_INTEGER;
    if (ordA !== ordB) return ordA - ordB;

    const lvlA = Number(a.level.replace(/\D/g, ""));
    const lvlB = Number(b.level.replace(/\D/g, ""));
    if (lvlA !== lvlB) return lvlA - lvlB;
    if (a.slug !== b.slug) return a.slug.localeCompare(b.slug);
    return a.partNumber - b.partNumber;
  });
}

function loadManifest(videosDir: string, hubDir: string): {
  manifestPath: string;
  hubManifestPath: string;
  payload: ManifestPayload;
} {
  const manifestPath = path.join(videosDir, "manifest.json");
  const hubManifestPath = path.join(hubDir, "data", "chapter-videos.json");
  const sourcePath = fs.existsSync(manifestPath)
    ? manifestPath
    : hubManifestPath;

  if (!fs.existsSync(sourcePath)) {
    throw new Error(`Could not find manifest.json at ${manifestPath} or ${hubManifestPath}`);
  }

  const payload = JSON.parse(
    fs.readFileSync(sourcePath, "utf-8")
  ) as ManifestPayload;
  return { manifestPath, hubManifestPath, payload };
}

function saveAndSyncManifest(
  manifestPath: string,
  hubManifestPath: string,
  payload: ManifestPayload
): void {
  payload.updatedAt = new Date().toISOString();
  payload.totalVideos = payload.videos.length;
  const serialized = JSON.stringify(payload, null, 2);

  fs.mkdirSync(path.dirname(manifestPath), { recursive: true });
  fs.writeFileSync(manifestPath, serialized, "utf-8");

  if (fs.existsSync(path.dirname(hubManifestPath))) {
    fs.writeFileSync(hubManifestPath, serialized, "utf-8");
  }
}

function loadClientConfig(): OAuthClientConfig {
  if (!fs.existsSync(CLIENT_SECRET_PATH)) {
    throw new Error(
      `Missing Google OAuth client secret file at:\n  ${CLIENT_SECRET_PATH}\n\n` +
        `Create an OAuth 2.0 "Desktop app" credential in Google Cloud Console with YouTube Data API v3 enabled,\n` +
        `download the JSON, and save it as .youtube-client-secret.json in video-studio/.`
    );
  }
  const raw = JSON.parse(fs.readFileSync(CLIENT_SECRET_PATH, "utf-8")) as {
    installed?: OAuthClientConfig;
    web?: OAuthClientConfig;
  };
  const cfg = raw.installed ?? raw.web;
  if (!cfg?.client_id || !cfg?.client_secret) {
    throw new Error(`Invalid OAuth client secret JSON in ${CLIENT_SECRET_PATH}`);
  }
  return cfg;
}

async function runOAuthFlow(): Promise<StoredToken> {
  const cfg = loadClientConfig();
  const authUri = cfg.auth_uri || "https://accounts.google.com/o/oauth2/v2/auth";
  const tokenUri = cfg.token_uri || "https://oauth2.googleapis.com/token";

  const authUrl = new URL(authUri);
  authUrl.searchParams.set("client_id", cfg.client_id);
  authUrl.searchParams.set("redirect_uri", OAUTH_REDIRECT_URI);
  authUrl.searchParams.set("response_type", "code");
  authUrl.searchParams.set("scope", OAUTH_SCOPES.join(" "));
  authUrl.searchParams.set("access_type", "offline");
  authUrl.searchParams.set("prompt", "consent");

  console.log("\nOpen this URL in your browser and authorize your YouTube channel:\n");
  console.log(`  ${authUrl.toString()}\n`);

  const code = await new Promise<string>((resolve, reject) => {
    const server = http.createServer((req, res) => {
      if (!req.url) return;
      const reqUrl = new URL(req.url, `http://127.0.0.1:${OAUTH_PORT}`);
      if (reqUrl.pathname !== "/oauth2callback") {
        res.writeHead(404);
        res.end("Not found");
        return;
      }

      const error = reqUrl.searchParams.get("error");
      const authCode = reqUrl.searchParams.get("code");
      if (error || !authCode) {
        res.writeHead(400, { "Content-Type": "text/html" });
        res.end("<h2>YouTube OAuth failed.</h2><p>You may close this tab.</p>");
        server.close();
        reject(new Error(`OAuth authorization failed: ${error ?? "missing code"}`));
        return;
      }

      res.writeHead(200, { "Content-Type": "text/html" });
      res.end(
        "<h2>YouTube OAuth connected!</h2><p>You can close this tab and return to your terminal.</p>"
      );
      server.close();
      resolve(authCode);
    });

    server.on("error", reject);
    server.listen(OAUTH_PORT, "127.0.0.1", () => {
      console.log(`Waiting for OAuth callback on ${OAUTH_REDIRECT_URI} ...`);
    });
  });

  const tokenRes = await fetch(tokenUri, {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: new URLSearchParams({
      code,
      client_id: cfg.client_id,
      client_secret: cfg.client_secret,
      redirect_uri: OAUTH_REDIRECT_URI,
      grant_type: "authorization_code",
    }),
  });

  if (!tokenRes.ok) {
    throw new Error(`Token exchange failed (${tokenRes.status}): ${await tokenRes.text()}`);
  }

  const data = (await tokenRes.json()) as {
    access_token: string;
    refresh_token?: string;
    expires_in: number;
    token_type: string;
    scope?: string;
  };

  const stored: StoredToken = {
    access_token: data.access_token,
    refresh_token: data.refresh_token,
    expires_at: Date.now() + (data.expires_in - 60) * 1000,
    token_type: data.token_type,
    scope: data.scope,
  };

  fs.writeFileSync(TOKEN_PATH, JSON.stringify(stored, null, 2), "utf-8");
  console.log(`[Auth] Saved OAuth token -> ${TOKEN_PATH}`);
  return stored;
}

async function getAccessToken(): Promise<string> {
  if (!fs.existsSync(TOKEN_PATH)) {
    throw new Error(
      `Not authenticated yet. Run "npm run youtube:auth" first to sign in to channel ${DEFAULT_CHANNEL_ID}.`
    );
  }

  const stored = JSON.parse(fs.readFileSync(TOKEN_PATH, "utf-8")) as StoredToken;
  if (stored.access_token && Date.now() < stored.expires_at) {
    return stored.access_token;
  }

  if (!stored.refresh_token) {
    throw new Error(
      `Saved OAuth token expired and has no refresh_token. Re-run "npm run youtube:auth".`
    );
  }

  const cfg = loadClientConfig();
  const tokenUri = cfg.token_uri || "https://oauth2.googleapis.com/token";
  const res = await fetch(tokenUri, {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: new URLSearchParams({
      client_id: cfg.client_id,
      client_secret: cfg.client_secret,
      refresh_token: stored.refresh_token,
      grant_type: "refresh_token",
    }),
  });

  if (!res.ok) {
    throw new Error(`Token refresh failed (${res.status}): ${await res.text()}`);
  }

  const refreshed = (await res.json()) as {
    access_token: string;
    expires_in: number;
    token_type?: string;
  };

  const updated: StoredToken = {
    ...stored,
    access_token: refreshed.access_token,
    expires_at: Date.now() + (refreshed.expires_in - 60) * 1000,
    token_type: refreshed.token_type ?? stored.token_type,
  };
  fs.writeFileSync(TOKEN_PATH, JSON.stringify(updated, null, 2), "utf-8");
  return updated.access_token;
}

async function verifyAuthenticatedChannel(
  accessToken: string,
  expectedChannelId: string
): Promise<{ id: string; title: string }> {
  const res = await fetch(
    "https://www.googleapis.com/youtube/v3/channels?part=snippet&mine=true",
    {
      headers: { Authorization: `Bearer ${accessToken}` },
    }
  );

  if (!res.ok) {
    throw new Error(`Failed to verify YouTube channel (${res.status}): ${await res.text()}`);
  }

  const data = (await res.json()) as {
    items?: Array<{ id: string; snippet?: { title?: string } }>;
  };
  const channel = data.items?.[0];
  if (!channel) {
    throw new Error("No YouTube channel found for the authenticated Google account.");
  }

  if (expectedChannelId && channel.id !== expectedChannelId) {
    throw new Error(
      `Channel mismatch! Authenticated as "${channel.snippet?.title}" (${channel.id}), ` +
        `but expected channel ${expectedChannelId}. Re-run "npm run youtube:auth" and select the right channel.`
    );
  }

  return { id: channel.id, title: channel.snippet?.title ?? channel.id };
}

function buildShortsTitle(entry: VideoManifestEntry): string {
  const suffix = " #Shorts";
  const cleanTitle = entry.partTitle.replace(/`/g, "").trim();
  const base = `${cleanTitle} (${entry.chapterTitle} Pt.${entry.partNumber})`;
  if (base.length + suffix.length <= 100) {
    return `${base}${suffix}`;
  }
  const shortBase = cleanTitle;
  if (shortBase.length + suffix.length <= 100) {
    return `${shortBase}${suffix}`;
  }
  return `${shortBase.slice(0, 100 - suffix.length - 1).trimEnd()}…${suffix}`;
}

function buildShortsDescription(entry: VideoManifestEntry): string {
  const levelNum = entry.level.replace(/\D/g, "");
  return [
    `${entry.chapterTitle} — Part ${entry.partNumber} of ${entry.totalParts}: ${entry.partTitle}`,
    "",
    `Full interactive guide & code examples:`,
    entry.roadmapUrl,
    "",
    `Hands-on Java 21 lab branch:`,
    `git checkout ${entry.branchName}`,
    "",
    `JavaHub Roadmap • Level ${levelNum}`,
    `#Shorts #Java #JavaHub #Programming #LearnJava #SoftwareEngineering`,
  ].join("\n");
}

function buildShortsTags(entry: VideoManifestEntry): string[] {
  return [
    "Java",
    "JavaHub",
    "Shorts",
    "Java Programming",
    "Learn Java",
    entry.level,
    entry.chapterTitle,
  ].slice(0, 15);
}

function computeNextScheduledPublishAt(
  entry: VideoManifestEntry,
  allVideos: VideoManifestEntry[],
  intervalDays: number,
  publishTimeUtc: string,
  startDate?: string
): string {
  // All parts of the same topic (chapterId) share the same publish day
  const sameTopicScheduled = allVideos.find(
    (v) => v.chapterId === entry.chapterId && Boolean(v.scheduledPublishAt)
  );
  if (sameTopicScheduled?.scheduledPublishAt) {
    return sameTopicScheduled.scheduledPublishAt;
  }

  const [hourStr, minStr] = publishTimeUtc.split(":");
  const hour = Number(hourStr ?? "14");
  const min = Number(minStr ?? "0");

  let latestMs = 0;
  for (const v of allVideos) {
    if (v.scheduledPublishAt) {
      const ms = Date.parse(v.scheduledPublishAt);
      if (!Number.isNaN(ms) && ms > latestMs) {
        latestMs = ms;
      }
    }
  }

  let nextDate: Date;
  if (latestMs > 0) {
    nextDate = new Date(latestMs + intervalDays * 24 * 60 * 60 * 1000);
  } else if (startDate) {
    nextDate = new Date(`${startDate}T00:00:00.000Z`);
  } else {
    nextDate = new Date();
    nextDate.setUTCDate(nextDate.getUTCDate() + 1);
  }

  nextDate.setUTCHours(hour, min, 0, 0);
  return nextDate.toISOString();
}

async function resolveVideoFilePath(
  entry: VideoManifestEntry,
  videosDir: string
): Promise<{ filePath: string; cleanup?: () => void }> {
  const localPath = path.join(videosDir, entry.file);
  if (fs.existsSync(localPath)) {
    return { filePath: localPath };
  }

  if (!entry.videoUrl) {
    throw new Error(`Local MP4 not found at ${localPath} and no videoUrl fallback is set.`);
  }

  console.log(`  [Download] Local MP4 missing; fetching from ${entry.videoUrl}...`);
  const res = await fetch(entry.videoUrl);
  if (!res.ok) {
    throw new Error(`Failed to download MP4 (${res.status}) from ${entry.videoUrl}`);
  }
  const tmpDir = fs.mkdtempSync(path.join(os.tmpdir(), "javahub-yt-"));
  const tmpFile = path.join(tmpDir, `${entry.partSlug}.mp4`);
  const buf = Buffer.from(await res.arrayBuffer());
  fs.writeFileSync(tmpFile, buf);
  return {
    filePath: tmpFile,
    cleanup: () => fs.rmSync(tmpDir, { recursive: true, force: true }),
  };
}

async function uploadShortToYouTube(
  accessToken: string,
  entry: VideoManifestEntry,
  mp4Path: string
): Promise<string> {
  const fileBuffer = fs.readFileSync(mp4Path);
  const metadata = {
    snippet: {
      title: buildShortsTitle(entry),
      description: buildShortsDescription(entry),
      tags: buildShortsTags(entry),
      categoryId: "27", // Education
      defaultLanguage: "en",
    },
    status: {
      privacyStatus: "unlisted",
      embeddable: true,
      selfDeclaredMadeForKids: false,
    },
  };

  // Step 1: Initiate resumable upload session
  const initRes = await fetch(
    "https://www.googleapis.com/upload/youtube/v3/videos?uploadType=resumable&part=snippet,status",
    {
      method: "POST",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/json; charset=UTF-8",
        "X-Upload-Content-Length": String(fileBuffer.byteLength),
        "X-Upload-Content-Type": "video/mp4",
      },
      body: JSON.stringify(metadata),
    }
  );

  if (!initRes.ok) {
    throw new Error(`Resumable upload init failed (${initRes.status}): ${await initRes.text()}`);
  }

  const uploadUrl = initRes.headers.get("location");
  if (!uploadUrl) {
    throw new Error("YouTube did not return a resumable upload Location header.");
  }

  // Step 2: Upload the MP4 payload
  const uploadRes = await fetch(uploadUrl, {
    method: "PUT",
    headers: {
      "Content-Length": String(fileBuffer.byteLength),
      "Content-Type": "video/mp4",
    },
    body: fileBuffer,
  });

  if (!uploadRes.ok) {
    throw new Error(`MP4 upload failed (${uploadRes.status}): ${await uploadRes.text()}`);
  }

  const created = (await uploadRes.json()) as { id?: string };
  if (!created.id) {
    throw new Error("YouTube upload succeeded but returned no video ID.");
  }
  return created.id;
}

async function publishVideoPublic(
  accessToken: string,
  youtubeId: string
): Promise<void> {
  const res = await fetch(
    "https://www.googleapis.com/youtube/v3/videos?part=status",
    {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/json; charset=UTF-8",
      },
      body: JSON.stringify({
        id: youtubeId,
        status: {
          privacyStatus: "public",
          embeddable: true,
          selfDeclaredMadeForKids: false,
        },
      }),
    }
  );

  if (!res.ok) {
    throw new Error(`Failed to publish ${youtubeId} (${res.status}): ${await res.text()}`);
  }
}

interface ChannelUploadedVideo {
  videoId: string;
  title: string;
  privacyStatus: "unlisted" | "public" | "private";
  publishedAt?: string;
}

async function listChannelUploads(
  accessToken: string
): Promise<ChannelUploadedVideo[]> {
  const chRes = await fetch(
    "https://www.googleapis.com/youtube/v3/channels?part=contentDetails&mine=true",
    { headers: { Authorization: `Bearer ${accessToken}` } }
  );
  if (!chRes.ok) {
    throw new Error(`Failed to fetch uploads playlist (${chRes.status}): ${await chRes.text()}`);
  }
  const chData = (await chRes.json()) as {
    items?: Array<{
      contentDetails?: { relatedPlaylists?: { uploads?: string } };
    }>;
  };
  const uploadsPlaylistId =
    chData.items?.[0]?.contentDetails?.relatedPlaylists?.uploads;
  if (!uploadsPlaylistId) {
    return [];
  }

  const results: ChannelUploadedVideo[] = [];
  let pageToken: string | undefined;

  do {
    const url = new URL("https://www.googleapis.com/youtube/v3/playlistItems");
    url.searchParams.set("part", "snippet,status");
    url.searchParams.set("playlistId", uploadsPlaylistId);
    url.searchParams.set("maxResults", "50");
    if (pageToken) url.searchParams.set("pageToken", pageToken);

    const res = await fetch(url.toString(), {
      headers: { Authorization: `Bearer ${accessToken}` },
    });
    if (!res.ok) {
      throw new Error(`Failed to list channel uploads (${res.status}): ${await res.text()}`);
    }
    const data = (await res.json()) as {
      nextPageToken?: string;
      items?: Array<{
        snippet?: {
          title?: string;
          publishedAt?: string;
          resourceId?: { videoId?: string };
        };
        status?: { privacyStatus?: string };
      }>;
    };

    for (const item of data.items ?? []) {
      const videoId = item.snippet?.resourceId?.videoId;
      if (!videoId) continue;
      const rawStatus = item.status?.privacyStatus;
      const privacyStatus: "unlisted" | "public" | "private" =
        rawStatus === "public" || rawStatus === "private"
          ? rawStatus
          : "unlisted";
      results.push({
        videoId,
        title: item.snippet?.title ?? "",
        privacyStatus,
        publishedAt: item.snippet?.publishedAt,
      });
    }

    pageToken = data.nextPageToken;
  } while (pageToken);

  return results;
}

async function updateShortMetadata(
  accessToken: string,
  youtubeId: string,
  entry: VideoManifestEntry,
  privacyStatus: "unlisted" | "public" | "private"
): Promise<void> {
  const res = await fetch(
    "https://www.googleapis.com/youtube/v3/videos?part=snippet,status",
    {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/json; charset=UTF-8",
      },
      body: JSON.stringify({
        id: youtubeId,
        snippet: {
          title: buildShortsTitle(entry),
          description: buildShortsDescription(entry),
          tags: buildShortsTags(entry),
          categoryId: "27",
          defaultLanguage: "en",
        },
        status: {
          privacyStatus,
          embeddable: true,
          selfDeclaredMadeForKids: false,
        },
      }),
    }
  );

  if (!res.ok) {
    throw new Error(`Failed to update metadata for ${youtubeId} (${res.status}): ${await res.text()}`);
  }
}

function normalizeMatchKey(text: string): string {
  return text
    .toLowerCase()
    .replace(/\.mp4$/i, "")
    .replace(/[^a-z0-9]+/g, " ")
    .trim();
}

function matchChannelVideoToEntry(
  ytTitle: string,
  candidates: VideoManifestEntry[]
): VideoManifestEntry | null {
  const normTitle = normalizeMatchKey(ytTitle);
  if (!normTitle) return null;

  for (const entry of candidates) {
    const expectedTitle = normalizeMatchKey(buildShortsTitle(entry));
    if (normTitle === expectedTitle) return entry;

    const fullSlugKey = normalizeMatchKey(
      `${entry.level} ${entry.slug} ${entry.partSlug}`
    );
    if (normTitle.includes(fullSlugKey)) return entry;

    const slugAndPart = normalizeMatchKey(`${entry.slug} ${entry.partSlug}`);
    if (normTitle.includes(slugAndPart)) return entry;
  }

  // Secondary fallback: unique partSlug match (only if unambiguous)
  const partMatches = candidates.filter((entry) =>
    normTitle.includes(normalizeMatchKey(entry.partSlug))
  );
  if (partMatches.length === 1) return partMatches[0];

  return null;
}

function extractYouTubeIdLoose(input: string): string {
  const trimmed = input.trim();
  if (/^[A-Za-z0-9_-]{11}$/.test(trimmed)) return trimmed;
  const parsed = new URL(trimmed);
  const v = parsed.searchParams.get("v");
  if (v && /^[A-Za-z0-9_-]{11}$/.test(v)) return v;
  const parts = parsed.pathname.split("/").filter(Boolean);
  const candidate = parts[parts.length - 1] ?? "";
  if (/^[A-Za-z0-9_-]{11}$/.test(candidate)) return candidate;
  throw new Error(`Could not extract a valid 11-char YouTube ID from "${input}"`);
}

async function main(): Promise<void> {
  const opts = parseArgs(process.argv.slice(2));
  const chapterOrder = loadRoadmapChapterOrder(opts.hubDir);

  if (opts.command === "auth") {
    const token = await runOAuthFlow();
    const channel = await verifyAuthenticatedChannel(
      token.access_token,
      opts.channelId
    );
    console.log(`[Auth] Verified YouTube channel: "${channel.title}" (${channel.id})`);
    return;
  }

  const { manifestPath, hubManifestPath, payload } = loadManifest(
    opts.videosDir,
    opts.hubDir
  );
  const ordered = sortVideosInRoadmapSequence(payload.videos, chapterOrder);

  if (opts.command === "status") {
    const uploaded = ordered.filter((v) => Boolean(v.youtubeId));
    const publicCount = uploaded.filter((v) => v.youtubeStatus === "public").length;
    const unlistedCount = uploaded.filter((v) => v.youtubeStatus !== "public").length;
    const remaining = ordered.filter((v) => !v.youtubeId);
    const now = Date.now();
    const dueNow = uploaded.filter(
      (v) =>
        v.youtubeStatus === "unlisted" &&
        v.scheduledPublishAt &&
        Date.parse(v.scheduledPublishAt) <= now
    );

    console.log(`\nJavaHub YouTube Shorts Status (Channel: ${opts.channelId})`);
    console.log(`  Total videos:    ${ordered.length}`);
    console.log(`  Uploaded:        ${uploaded.length} (${unlistedCount} unlisted, ${publicCount} public)`);
    console.log(`  Due to publish:  ${dueNow.length}`);
    console.log(`  Remaining:       ${remaining.length}\n`);

    if (remaining.length > 0) {
      console.log(`Next ${Math.min(10, remaining.length)} videos in roadmap sequence:`);
      remaining.slice(0, 10).forEach((v, i) => {
        console.log(
          `  ${String(i + 1).padStart(2, " ")}. [${v.chapterId} Pt.${v.partNumber}/${v.totalParts}] ${v.partTitle}`
        );
      });
    }
    return;
  }

  if (opts.command === "set-id") {
    if (!opts.chapter || !opts.partNumber || !opts.youtubeId) {
      throw new Error(
        `Usage: npm run youtube:set-id -- --chapter level-0/environment-setup --part 1 --id <VIDEO_ID>`
      );
    }
    const ytId = extractYouTubeIdLoose(opts.youtubeId);
    const entry = payload.videos.find(
      (v) => v.chapterId === opts.chapter && v.partNumber === opts.partNumber
    );
    if (!entry) {
      throw new Error(`No video found for ${opts.chapter} part ${opts.partNumber}`);
    }
    entry.youtubeId = ytId;
    entry.youtubeUrl = `https://www.youtube.com/shorts/${ytId}`;
    entry.youtubeStatus = opts.status;
    if (opts.status === "unlisted" && !entry.scheduledPublishAt) {
      entry.scheduledPublishAt = computeNextScheduledPublishAt(
        entry,
        payload.videos,
        opts.intervalDays,
        opts.publishTimeUtc,
        opts.startDate
      );
    }
    if (opts.status === "public") {
      entry.publishedAt = new Date().toISOString();
    }
    saveAndSyncManifest(manifestPath, hubManifestPath, payload);
    console.log(
      `[Synced] ${entry.chapterId} Pt.${entry.partNumber} -> ${entry.youtubeUrl} (${entry.youtubeStatus})`
    );
    return;
  }

  if (opts.command === "stage") {
    let queue = ordered.filter((v) => !v.youtubeId);
    if (opts.chapter) {
      queue = queue.filter((v) => v.chapterId === opts.chapter);
    }
    if (opts.partNumber !== undefined) {
      queue = queue.filter((v) => v.partNumber === opts.partNumber);
    }
    const batch = queue.slice(0, opts.limit);

    if (batch.length === 0) {
      console.log("[Stage] Nothing to stage — all matching videos already have YouTube IDs.");
      return;
    }

    if (!opts.dryRun) {
      fs.rmSync(opts.stageDir, { recursive: true, force: true });
      fs.mkdirSync(opts.stageDir, { recursive: true });
    }

    const seqIndexByKey = new Map<string, number>();
    ordered.forEach((v, idx) => {
      seqIndexByKey.set(`${v.chapterId}::${v.partNumber}`, idx + 1);
    });

    console.log(
      `\n[Stage] Preparing ${batch.length} Short(s) for YouTube Studio drag-and-drop${
        opts.dryRun ? " [DRY RUN]" : ""
      }...\n`
    );

    for (const item of batch) {
      const seqNum = String(
        seqIndexByKey.get(`${item.chapterId}::${item.partNumber}`) ?? 0
      ).padStart(3, "0");
      const stagedName = `${seqNum}__${item.level}__${item.slug}__${item.partSlug}.mp4`;
      const destPath = path.join(opts.stageDir, stagedName);
      console.log(`• ${stagedName}`);

      if (opts.dryRun) continue;

      const { filePath, cleanup } = await resolveVideoFilePath(item, opts.videosDir);
      try {
        fs.copyFileSync(filePath, destPath);
      } finally {
        cleanup?.();
      }
    }

    console.log(`\n[Done] Staged ${batch.length} MP4 file(s) in:\n  ${opts.stageDir}`);
    console.log(
      `\nNext steps:\n` +
        `  1. Open YouTube Studio -> Create -> Upload videos\n` +
        `  2. Select all files in that folder (Ctrl+A) and drag them in (Upload default: Unlisted)\n` +
        `  3. Once uploads finish, run: npm run youtube:sync-channel\n`
    );
    return;
  }

  if (opts.command === "sync-channel") {
    const accessToken = await getAccessToken();
    const channel = await verifyAuthenticatedChannel(accessToken, opts.channelId);
    console.log(`[Auth] Verified channel: "${channel.title}" (${channel.id})`);

    const channelVideos = await listChannelUploads(accessToken);
    console.log(`[Sync] Found ${channelVideos.length} video(s) on channel.`);

    const byYoutubeId = new Map<string, VideoManifestEntry>();
    for (const v of payload.videos) {
      if (v.youtubeId) byYoutubeId.set(v.youtubeId, v);
    }

    let linkedCount = 0;
    let statusUpdatedCount = 0;
    let metadataUpdatedCount = 0;
    const unmatched: ChannelUploadedVideo[] = [];

    const seqRank = new Map<string, number>();
    ordered.forEach((v, idx) => {
      seqRank.set(`${v.chapterId}::${v.partNumber}`, idx);
    });

    const pendingMatches: Array<{
      ytVid: ChannelUploadedVideo;
      match: VideoManifestEntry;
      rank: number;
    }> = [];
    const claimedKeys = new Set<string>();

    for (const ytVid of channelVideos) {
      const existing = byYoutubeId.get(ytVid.videoId);
      if (existing) {
        if (existing.youtubeStatus !== ytVid.privacyStatus) {
          console.log(
            `• [Status] ${existing.chapterId} Pt.${existing.partNumber} (${ytVid.videoId}): ${existing.youtubeStatus} -> ${ytVid.privacyStatus}`
          );
          if (!opts.dryRun) {
            existing.youtubeStatus = ytVid.privacyStatus;
            if (ytVid.privacyStatus === "public" && !existing.publishedAt) {
              existing.publishedAt = ytVid.publishedAt ?? new Date().toISOString();
            }
          }
          statusUpdatedCount++;
        }
        continue;
      }

      const unlinkedCandidates = ordered.filter(
        (v) => !v.youtubeId && !claimedKeys.has(`${v.chapterId}::${v.partNumber}`)
      );
      const match = matchChannelVideoToEntry(ytVid.title, unlinkedCandidates);
      if (!match) {
        unmatched.push(ytVid);
        continue;
      }

      const key = `${match.chapterId}::${match.partNumber}`;
      claimedKeys.add(key);
      pendingMatches.push({
        ytVid,
        match,
        rank: seqRank.get(key) ?? Number.MAX_SAFE_INTEGER,
      });
    }

    // Assign sequential daily publish dates in true roadmap order
    pendingMatches.sort((a, b) => a.rank - b.rank);

    for (const { ytVid, match } of pendingMatches) {
      const targetPrivacy: "unlisted" | "public" | "private" =
        ytVid.privacyStatus === "public" ? "public" : "unlisted";
      const scheduledAt =
        match.scheduledPublishAt ??
        computeNextScheduledPublishAt(
          match,
          payload.videos,
          opts.intervalDays,
          opts.publishTimeUtc,
          opts.startDate
        );

      console.log(
        `• [Match] "${ytVid.title}" (${ytVid.videoId}) -> ${match.chapterId} Pt.${match.partNumber} (planned public: ${scheduledAt.slice(0, 10)})`
      );

      if (!opts.dryRun) {
        if (opts.updateMetadata) {
          await updateShortMetadata(accessToken, ytVid.videoId, match, targetPrivacy);
          metadataUpdatedCount++;
        }
        match.youtubeId = ytVid.videoId;
        match.youtubeUrl = `https://www.youtube.com/shorts/${ytVid.videoId}`;
        match.youtubeStatus = targetPrivacy;
        match.scheduledPublishAt = scheduledAt;
        if (targetPrivacy === "public") {
          match.publishedAt = ytVid.publishedAt ?? new Date().toISOString();
        }
        byYoutubeId.set(ytVid.videoId, match);
        saveAndSyncManifest(manifestPath, hubManifestPath, payload);
      } else {
        match.scheduledPublishAt = scheduledAt;
      }
      linkedCount++;
    }

    if (!opts.dryRun && statusUpdatedCount > 0) {
      saveAndSyncManifest(manifestPath, hubManifestPath, payload);
    }

    console.log(
      `\n[Done] Linked: ${linkedCount}, Metadata updated: ${metadataUpdatedCount}, Status synced: ${statusUpdatedCount}, Unmatched: ${unmatched.length}`
    );
    if (unmatched.length > 0) {
      console.log("  Unmatched channel videos:");
      unmatched.slice(0, 10).forEach((u) => {
        console.log(`    - ${u.videoId}: "${u.title}"`);
      });
    }
    return;
  }

  if (opts.command === "reschedule") {
    const [hourStr, minStr] = opts.publishTimeUtc.split(":");
    const hour = Number(hourStr ?? "14");
    const min = Number(minStr ?? "0");

    const baseDate = opts.startDate
      ? new Date(`${opts.startDate}T00:00:00.000Z`)
      : (() => {
          const d = new Date();
          d.setUTCDate(d.getUTCDate() + 1);
          return d;
        })();
    baseDate.setUTCHours(hour, min, 0, 0);

    // Group uploaded unlisted videos by chapterId in exact roadmap sequence
    const uploadedUnlisted = ordered.filter(
      (v) => Boolean(v.youtubeId) && v.youtubeStatus !== "public"
    );
    const chapterDayIndex = new Map<string, number>();
    let dayOffset = 0;

    for (const item of uploadedUnlisted) {
      if (!chapterDayIndex.has(item.chapterId)) {
        chapterDayIndex.set(item.chapterId, dayOffset);
        dayOffset += opts.intervalDays;
      }
      const assignedDay = chapterDayIndex.get(item.chapterId)!;
      const dt = new Date(baseDate.getTime() + assignedDay * 24 * 60 * 60 * 1000);
      if (!opts.dryRun) {
        item.scheduledPublishAt = dt.toISOString();
      }
    }

    if (!opts.dryRun) {
      saveAndSyncManifest(manifestPath, hubManifestPath, payload);
    }

    console.log(
      `\n[Reschedule] Assigned ${uploadedUnlisted.length} unlisted video(s) across ${chapterDayIndex.size} topic(s) (1 topic every ${opts.intervalDays} day(s))${
        opts.dryRun ? " [DRY RUN]" : ""
      }:`
    );
    let shown = 0;
    for (const [chapterId, offset] of chapterDayIndex.entries()) {
      const dt = new Date(baseDate.getTime() + offset * 24 * 60 * 60 * 1000);
      const count = uploadedUnlisted.filter((v) => v.chapterId === chapterId).length;
      if (shown < 12) {
        console.log(`  • ${dt.toISOString().slice(0, 10)} — ${chapterId} (${count} parts)`);
      }
      shown++;
    }
    if (shown > 12) {
      console.log(`  ... and ${shown - 12} more topic(s).`);
    }
    return;
  }

  if (opts.command === "upload") {
    let queue = ordered.filter((v) => !v.youtubeId);
    if (opts.chapter) {
      queue = queue.filter((v) => v.chapterId === opts.chapter);
    }
    if (opts.partNumber !== undefined) {
      queue = queue.filter((v) => v.partNumber === opts.partNumber);
    }
    const batch = queue.slice(0, opts.limit);

    if (batch.length === 0) {
      console.log("[Upload] Nothing to upload — matching videos already have YouTube IDs.");
      return;
    }

    let accessToken = "";
    if (!opts.dryRun) {
      accessToken = await getAccessToken();
      const channel = await verifyAuthenticatedChannel(accessToken, opts.channelId);
      console.log(`[Auth] Verified channel: "${channel.title}" (${channel.id})`);
    }

    console.log(
      `\n[Upload] Processing ${batch.length} Short(s) as UNLISTED (every ${opts.intervalDays} days cadence)${
        opts.dryRun ? " [DRY RUN]" : ""
      }...\n`
    );

    for (const item of batch) {
      const scheduledAt = computeNextScheduledPublishAt(
        item,
        payload.videos,
        opts.intervalDays,
        opts.publishTimeUtc,
        opts.startDate
      );
      const title = buildShortsTitle(item);
      console.log(
        `• [${item.chapterId} Pt.${item.partNumber}/${item.totalParts}] "${title}" -> planned public: ${scheduledAt.slice(0, 10)}`
      );

      if (opts.dryRun) {
        item.scheduledPublishAt = scheduledAt;
        continue;
      }

      const { filePath, cleanup } = await resolveVideoFilePath(item, opts.videosDir);
      try {
        const ytId = await uploadShortToYouTube(accessToken, item, filePath);
        item.youtubeId = ytId;
        item.youtubeUrl = `https://www.youtube.com/shorts/${ytId}`;
        item.youtubeStatus = "unlisted";
        item.scheduledPublishAt = scheduledAt;
        saveAndSyncManifest(manifestPath, hubManifestPath, payload);
        console.log(`  [OK] Uploaded UNLISTED -> ${item.youtubeUrl}`);
      } finally {
        cleanup?.();
      }
    }

    console.log("\n[Done] Manifest & java_hub/data/chapter-videos.json synced.");
    return;
  }

  if (opts.command === "publish-due") {
    const now = Date.now();
    const due = ordered
      .filter(
        (v) =>
          v.youtubeId &&
          v.youtubeStatus === "unlisted" &&
          v.scheduledPublishAt &&
          Date.parse(v.scheduledPublishAt) <= now
      )
      .slice(0, opts.limit);

    if (due.length === 0) {
      console.log("[Publish] No unlisted Shorts are due to be published right now.");
      return;
    }

    let accessToken = "";
    if (!opts.dryRun) {
      accessToken = await getAccessToken();
      const channel = await verifyAuthenticatedChannel(accessToken, opts.channelId);
      console.log(`[Auth] Verified channel: "${channel.title}" (${channel.id})`);
    }

    for (const item of due) {
      console.log(
        `• Publishing [${item.chapterId} Pt.${item.partNumber}] ${item.youtubeUrl} (scheduled ${item.scheduledPublishAt})`
      );
      if (opts.dryRun) continue;
      await publishVideoPublic(accessToken, item.youtubeId!);
      item.youtubeStatus = "public";
      item.publishedAt = new Date().toISOString();
      saveAndSyncManifest(manifestPath, hubManifestPath, payload);
      console.log(`  [OK] Now PUBLIC -> ${item.youtubeUrl}`);
    }
  }
}

main().catch((err) => {
  console.error("\n[Error]", err instanceof Error ? err.message : err);
  process.exit(1);
});
