import fs from "node:fs";
import path from "node:path";

const SAMPLE_RATE = 44100;

function writeMonoWav(filePath: string, samples: Float32Array): void {
  const numSamples = samples.length;
  const dataSize = numSamples * 2;
  const buf = Buffer.alloc(44 + dataSize);

  buf.write("RIFF", 0);
  buf.writeUInt32LE(36 + dataSize, 4);
  buf.write("WAVE", 8);
  buf.write("fmt ", 12);
  buf.writeUInt32LE(16, 16); // PCM chunk size
  buf.writeUInt16LE(1, 20); // PCM format
  buf.writeUInt16LE(1, 22); // Mono
  buf.writeUInt32LE(SAMPLE_RATE, 24);
  buf.writeUInt32LE(SAMPLE_RATE * 2, 28); // byte rate
  buf.writeUInt16LE(2, 32); // block align
  buf.writeUInt16LE(16, 34); // bits per sample
  buf.write("data", 36);
  buf.writeUInt32LE(dataSize, 40);

  for (let i = 0; i < numSamples; i++) {
    const clamped = Math.max(-1, Math.min(1, samples[i]));
    buf.writeInt16LE(Math.round(clamped * 32767), 44 + i * 2);
  }

  fs.writeFileSync(filePath, buf);
}

/** Soft filtered air whoosh for snappy scene transitions */
function synthWhoosh(): Float32Array {
  const dur = 0.24;
  const n = Math.floor(SAMPLE_RATE * dur);
  const out = new Float32Array(n);
  let prev = 0;
  for (let i = 0; i < n; i++) {
    const t = i / n;
    const env = Math.sin(Math.PI * t) ** 1.6;
    const white = Math.random() * 2 - 1;
    // Moving low-pass smoothing
    const alpha = 0.04 + 0.14 * Math.sin(Math.PI * t);
    prev = prev + alpha * (white - prev);
    out[i] = prev * env * 0.55;
  }
  return out;
}

/** Crisp UI pop for hook slams & card reveals */
function synthPop(): Float32Array {
  const dur = 0.11;
  const n = Math.floor(SAMPLE_RATE * dur);
  const out = new Float32Array(n);
  let phase = 0;
  for (let i = 0; i < n; i++) {
    const t = i / SAMPLE_RATE;
    const freq = 240 + 480 * Math.exp(-t * 45);
    phase += (2 * Math.PI * freq) / SAMPLE_RATE;
    const env = Math.exp(-t * 36);
    out[i] = Math.sin(phase) * env * 0.5;
  }
  return out;
}

/** Subtle mechanical keyclick burst for code & terminal beats */
function synthKeyclick(): Float32Array {
  const dur = 0.18;
  const n = Math.floor(SAMPLE_RATE * dur);
  const out = new Float32Array(n);
  const clickStarts = [0.0, 0.055, 0.11];
  for (const startSec of clickStarts) {
    const startIdx = Math.floor(startSec * SAMPLE_RATE);
    const len = Math.floor(0.025 * SAMPLE_RATE);
    for (let j = 0; j < len && startIdx + j < n; j++) {
      const t = j / SAMPLE_RATE;
      const env = Math.exp(-t * 220);
      const tone = Math.sin(2 * Math.PI * 1650 * t) * 0.4 + (Math.random() * 2 - 1) * 0.6;
      out[startIdx + j] += tone * env * 0.35;
    }
  }
  return out;
}

/** Warm two-note chime for DO / success / CTA */
function synthSuccess(): Float32Array {
  const dur = 0.34;
  const n = Math.floor(SAMPLE_RATE * dur);
  const out = new Float32Array(n);
  for (let i = 0; i < n; i++) {
    const t = i / SAMPLE_RATE;
    const n1 = Math.sin(2 * Math.PI * 587.33 * t) * Math.exp(-t * 14);
    const t2 = Math.max(0, t - 0.075);
    const n2 = t >= 0.075 ? Math.sin(2 * Math.PI * 880 * t2) * Math.exp(-t2 * 11) : 0;
    out[i] = (n1 * 0.35 + n2 * 0.42);
  }
  return out;
}

/** Low subtle warning thud for DON'T / pitfall beats */
function synthError(): Float32Array {
  const dur = 0.24;
  const n = Math.floor(SAMPLE_RATE * dur);
  const out = new Float32Array(n);
  for (let i = 0; i < n; i++) {
    const t = i / SAMPLE_RATE;
    const freq = 155 - 45 * (t / dur);
    const env = Math.exp(-t * 16);
    out[i] = (Math.sin(2 * Math.PI * freq * t) + 0.3 * Math.sin(2 * Math.PI * freq * 1.5 * t)) * env * 0.42;
  }
  return out;
}

export function ensureProceduralSfx(publicDir: string): void {
  const sfxDir = path.join(publicDir, "sfx");
  fs.mkdirSync(sfxDir, { recursive: true });

  const bank: Record<string, () => Float32Array> = {
    "whoosh.wav": synthWhoosh,
    "pop.wav": synthPop,
    "keyclick.wav": synthKeyclick,
    "success.wav": synthSuccess,
    "error.wav": synthError,
  };

  for (const [filename, generator] of Object.entries(bank)) {
    const target = path.join(sfxDir, filename);
    if (!fs.existsSync(target)) {
      writeMonoWav(target, generator());
    }
  }
}
