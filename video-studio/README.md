# JavaHub Shorts Video Studio (100% Local & Open-Source)

Vertical-first **9:16 YouTube Shorts (`1080×1920`)** generator for the [JavaHub Developer Roadmap](https://ravishankar2463.github.io/java_hub/).

- **Voiceover:** Local **Kokoro-82M (ONNX)** at crisp conversational cadence (`am_michael`, `1.1x`), with automatic drop-in `.human.wav` override support per beat.
- **Sound Design:** Procedural zero-dependency WAV SFX (`whoosh`, `pop`, `keyclick`, `success`, `error`).
- **Visual Craft:** Senior Dev IDE + Whiteboard + Fireship-style kinetic cuts:
  - `hook` — Kinetic slam headline with highlighted keyword
  - `code-spotlight` — IntelliJ-style IDE with multi-step active line spotlighting & live callout cards
  - `flow-diagram` — Sequentially animated architecture/JVM pipeline diagrams
  - `terminal` — Simulated bash terminal with typed commands & bytecode/output reveals
  - `compare` — `❌ BUG / ✅ SAFE` & before/after stacked comparison cards
  - `cta` — Recap checklist, `git checkout` lab command & next-part teaser
  - **YouTube Shorts Safe Zones** + word-highlighted kinetic karaoke captions.

## Usage

```powershell
cd video-studio
npm install

# List all 116 chapters from ../java_hub and see which have storyboards
npm run list

# Render all parts of a chapter storyboard -> ../videos/<level>/<slug>/part-*.mp4 + manifest.json
npm run generate -- --chapter level-0/anatomy-of-a-java-program

# Render only a single part
npm run generate -- --chapter level-0/anatomy-of-a-java-program --part 1

# Override Kokoro voice or speed
npm run generate -- --chapter level-0/anatomy-of-a-java-program --voice am_adam --speed 1.12

# Open interactive Remotion preview in browser
npm run studio
```

## Optional: Drop-In Human Voiceover

To replace any beat's Kokoro TTS with your own voice recording, place a `.wav` file at:
```text
video-studio/public/audio/<level>__<slug>__part-<N>/<beat-id>.human.wav
```
The renderer automatically detects it, measures its exact duration, and syncs all animations and captions to your voice.
