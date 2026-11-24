import fs from "node:fs";
import path from "node:path";
import { KokoroTTS } from "kokoro-js";
import type { PartStoryboard } from "../src/types.js";

const MODEL_ID = "onnx-community/Kokoro-82M-v1.0-ONNX";
/** Snappy YouTube Shorts breath gap between beats */
const BEAT_PADDING_SEC = 0.16;

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

    console.log(
      `[TTS] (${i + 1}/${part.beats.length}) ${filename} (${voice}, ${speed}x)...`
    );
    const audio = await tts.generate(beat.narration, {
      voice: voice as Parameters<KokoroTTS["generate"]>[1] extends { voice?: infer V } ? V : never,
      speed,
    });
    await audio.save(absPath);

    const exactDuration = getWavDurationSec(absPath);
    const paddedDuration = Number((exactDuration + BEAT_PADDING_SEC).toFixed(2));
    console.log(
      `[TTS]   -> ${exactDuration.toFixed(2)}s audio (beat: ${paddedDuration.toFixed(2)}s)`
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
