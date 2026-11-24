import fs from "node:fs";
import path from "node:path";
import { KokoroTTS } from "kokoro-js";
import type { PartStoryboard } from "../src/types.js";

const MODEL_ID = "onnx-community/Kokoro-82M-v1.0-ONNX";
/** Snappy YouTube Shorts breath gap between beats */
const BEAT_PADDING_SEC = 0.16;

/**
 * Common English homographs & Java tech tokens that TTS models can mispronounce.
 * - `live` ("live inside a class" /lɪv/ vs "live traffic" /laɪv/)
 * - `read` (/riːd/ vs /rɛd/), `lead` (/liːd/ vs /lɛd/), `close` (/kloʊz/ vs /kloʊs/)
 * - `record` (Java record noun vs verb), `invalid`, `use`, `wound`
 */
const HOMOGRAPH_WARNINGS: Array<{ pattern: RegExp; tip: string }> = [
  {
    pattern: /\blive\b(?!\s+(?:reload|traffic|stream|demo|server|production|coding|update|data|metrics|view))/i,
    tip: '"live" can be mispronounced as /laɪv/ ("live TV") instead of /lɪv/ — consider "reside", "sit", or "belong", or set beat.ttsNarration.',
  },
  {
    pattern: /\bread\b/i,
    tip: '"read" is a homograph (/riːd/ vs /rɛd/) — verify tense pronunciation or set beat.ttsNarration.',
  },
  {
    pattern: /\b(lead|close|record|invalid)\b/i,
    tip: 'Contains a homograph ("lead", "close", "record", or "invalid") — spot-check audio or set beat.ttsNarration if mispronounced.',
  },
];

const SPOKEN_NORMALIZERS: Array<[RegExp, string]> = [
  // Verb "live" in spatial Java contexts -> unambiguous "reside" for TTS only (captions stay unchanged)
  [/\blive\s+(inside|in|on|within|under|outside)\b/gi, "reside $1"],
  [/\blives\s+(inside|in|on|within|under|outside)\b/gi, "resides $1"],
  // Common Java CLI / Big-O tokens
  [/\bjavac\b/g, "java c"],
  [/\bjavap\b/g, "java p"],
  [/\bjavadoc\b/gi, "java doc"],
  [/\bNIO\.2\b/g, "N I O 2"],
  [/\bO\(1\)/g, "O of 1"],
  [/\bO\(n\)/gi, "O of N"],
  [/\bO\(log\s*n\)/gi, "O of log N"],
  [/\bO\(n\s*log\s*n\)/gi, "O of N log N"],
];

export function normalizeTextForTts(
  text: string,
  beatId: string
): string {
  let spoken = text;

  for (const [pattern, replacement] of SPOKEN_NORMALIZERS) {
    if (pattern.test(spoken)) {
      spoken = spoken.replace(pattern, replacement);
    }
  }

  for (const { pattern, tip } of HOMOGRAPH_WARNINGS) {
    if (pattern.test(spoken)) {
      console.warn(`[TTS Homograph Check] (${beatId}) ${tip}`);
    }
  }

  return spoken;
}

let cachedTts: KokoroTTS | null = null;

async function getTtsEngine(): Promise<KokoroTTS> {
  if (cachedTts) return cachedTts;
  console.log(`[TTS] Loading Kokoro-82M (${MODEL_ID}, fp32, cpu)...`);
  cachedTts = await KokoroTTS.from_pretrained(MODEL_ID, {
    dtype: "fp32",
    device: "cpu",
  });
  console.log("[TTS] Kokoro-82M ready.");
  return cachedTts;
}

export function getWavDurationSec(wavPath: string): number {
  const buf = fs.readFileSync(wavPath);
  if (buf.length < 44 || buf.toString("ascii", 0, 4) !== "RIFF") {
    throw new Error(`Invalid WAV file: ${wavPath}`);
  }

  let offset = 12;
  let byteRate = 0;
  let dataSize = 0;

  while (offset + 8 <= buf.length) {
    const chunkId = buf.toString("ascii", offset, offset + 4);
    const chunkSize = buf.readUInt32LE(offset + 4);
    if (chunkId === "fmt " && offset + 24 <= buf.length) {
      byteRate = buf.readUInt32LE(offset + 16);
    } else if (chunkId === "data") {
      dataSize = chunkSize;
      break;
    }
    offset += 8 + chunkSize + (chunkSize % 2);
  }

  if (byteRate <= 0 || dataSize <= 0) {
    throw new Error(`Could not determine WAV duration for: ${wavPath}`);
  }
  return dataSize / byteRate;
}

export async function synthesizePartAudio(
  part: PartStoryboard,
  publicDir: string,
  defaultVoice = "af_heart",
  defaultSpeed = 1.24
): Promise<PartStoryboard> {
  const voice = part.voice || defaultVoice;
  const speed = part.speed ?? defaultSpeed;
  const safeFolder = `${part.level}__${part.slug}__part-${part.partNumber}`;
  const audioRelDir = path.posix.join("audio", safeFolder);
  const audioAbsDir = path.join(publicDir, "audio", safeFolder);

  fs.mkdirSync(audioAbsDir, { recursive: true });

  const updatedBeats = [];
  for (let i = 0; i < part.beats.length; i++) {
    const beat = part.beats[i];

    // Support drop-in human voiceover override: <beat.id>.human.wav
    const humanFilename = `${beat.id}.human.wav`;
    const humanAbsPath = path.join(audioAbsDir, humanFilename);
    if (fs.existsSync(humanAbsPath)) {
      const exactDuration = getWavDurationSec(humanAbsPath);
      const padded = Number((exactDuration + BEAT_PADDING_SEC).toFixed(2));
      console.log(
        `[Audio] (${i + 1}/${part.beats.length}) Using human override ${humanFilename} (${exactDuration.toFixed(2)}s)`
      );
      updatedBeats.push({
        ...beat,
        audioFile: path.posix.join(audioRelDir, humanFilename),
        durationSec: padded,
      });
      continue;
    }

    const tts = await getTtsEngine();
    const filename = `${beat.id}.wav`;
    const absPath = path.join(audioAbsDir, filename);
    const relPath = path.posix.join(audioRelDir, filename);

    const spokenText = beat.ttsNarration
      ? beat.ttsNarration
      : normalizeTextForTts(beat.narration, beat.id);

    const beatStart = Date.now();
    console.log(
      `[TTS] (${i + 1}/${part.beats.length}) Synthesizing ${filename} (${voice}, ${speed}x)...`
    );
    const audio = await tts.generate(spokenText, {
      voice: voice as Parameters<KokoroTTS["generate"]>[1] extends { voice?: infer V } ? V : never,
      speed,
    });
    await audio.save(absPath);

    const exactDuration = getWavDurationSec(absPath);
    const paddedDuration = Number((exactDuration + BEAT_PADDING_SEC).toFixed(2));
    const genSec = ((Date.now() - beatStart) / 1000).toFixed(1);
    console.log(
      `[TTS]   -> ${exactDuration.toFixed(2)}s audio (beat: ${paddedDuration.toFixed(2)}s, synthesized in ${genSec}s)`
    );

    updatedBeats.push({
      ...beat,
      audioFile: relPath,
      durationSec: paddedDuration,
    });
  }

  return {
    ...part,
    voice,
    speed,
    beats: updatedBeats,
  };
}
