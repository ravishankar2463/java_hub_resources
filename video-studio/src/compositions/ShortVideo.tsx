import React from "react";
import {
  AbsoluteFill,
  Audio,
  Sequence,
  interpolate,
  staticFile,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME } from "../theme";
import type { ShortVideoProps, StoryboardBeat } from "../types";
import { HookBeatView } from "./scenes/HookBeatView";
import { CodeSpotlightBeatView } from "./scenes/CodeSpotlightBeatView";
import { FlowDiagramBeatView } from "./scenes/FlowDiagramBeatView";
import { TerminalBeatView } from "./scenes/TerminalBeatView";
import { CompareBeatView } from "./scenes/CompareBeatView";
import { CtaBeatView } from "./scenes/CtaBeatView";

const SFX_VOLUME = {
  whoosh: 0.14,
  pop: 0.16,
  keyclick: 0.16,
  success: 0.18,
  error: 0.18,
} as const;

/**
 * Kinetic word-highlighted YouTube Shorts captions positioned safely above
 * the YouTube Shorts bottom overlay and left of the right-hand action bar.
 */
const KineticCaptions: React.FC<{
  narration: string;
  durationInFrames: number;
}> = ({ narration, durationInFrames }) => {
  const frame = useCurrentFrame();
  const words = narration.trim().split(/\s+/).filter(Boolean);
  if (words.length === 0) return null;

  const usableFrames = Math.max(1, durationInFrames - 8);
  const activeWordIdx = Math.min(
    words.length - 1,
    Math.floor((frame / usableFrames) * words.length)
  );

  // Group into snappy 4-word punch chunks
  const chunkSize = 4;
  const chunkIdx = Math.floor(activeWordIdx / chunkSize);
  const start = chunkIdx * chunkSize;
  const chunkWords = words.slice(start, start + chunkSize);

  const opacity = interpolate(
    frame,
    [0, 4, durationInFrames - 5, durationInFrames],
    [0, 1, 1, 0],
    { extrapolateLeft: "clamp", extrapolateRight: "clamp" }
  );

  return (
    <div
      style={{
        position: "absolute",
        left: 44,
        right: 112, // YouTube Shorts right-side buttons safe zone
        bottom: 245, // YouTube Shorts bottom title/channel safe zone
        display: "flex",
        justifyContent: "center",
        opacity,
        pointerEvents: "none",
        zIndex: 40,
      }}
    >
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",
          gap: "8px 14px",
          padding: "16px 26px",
          borderRadius: 18,
          background: "rgba(5, 8, 15, 0.88)",
          border: `1.5px solid rgba(148,163,184,0.24)`,
          boxShadow: "0 14px 36px rgba(0,0,0,0.6)",
        }}
      >
        {chunkWords.map((word, idx) => {
          const globalIdx = start + idx;
          const isActive = globalIdx === activeWordIdx;
          return (
            <span
              key={globalIdx}
              style={{
                fontSize: 42,
                fontWeight: 900,
                lineHeight: 1.2,
                letterSpacing: "-0.01em",
                color: isActive ? THEME.amber : THEME.text,
                transform: isActive ? "scale(1.08)" : "scale(1)",
                textShadow: isActive
                  ? "0 0 18px rgba(245,158,11,0.45)"
                  : "0 2px 6px rgba(0,0,0,0.8)",
                display: "inline-block",
              }}
            >
              {word}
            </span>
          );
        })}
      </div>
    </div>
  );
};

const BeatRenderer: React.FC<{
  beat: StoryboardBeat;
  durationInFrames: number;
}> = ({ beat, durationInFrames }) => {
  const sfx = beat.sfx && beat.sfx !== "none" ? beat.sfx : undefined;

  return (
    <AbsoluteFill>
      {beat.audioFile ? <Audio src={staticFile(beat.audioFile)} volume={1} /> : null}
      {sfx ? (
        <Audio src={staticFile(`sfx/${sfx}.wav`)} volume={SFX_VOLUME[sfx]} />
      ) : null}

      {beat.kind === "hook" && <HookBeatView beat={beat} />}
      {beat.kind === "code-spotlight" && (
        <CodeSpotlightBeatView beat={beat} durationInFrames={durationInFrames} />
      )}
      {beat.kind === "flow-diagram" && (
        <FlowDiagramBeatView beat={beat} durationInFrames={durationInFrames} />
      )}
      {beat.kind === "terminal" && (
        <TerminalBeatView beat={beat} durationInFrames={durationInFrames} />
      )}
      {beat.kind === "compare" && (
        <CompareBeatView beat={beat} durationInFrames={durationInFrames} />
      )}
      {beat.kind === "cta" && <CtaBeatView beat={beat} />}

      <KineticCaptions
        narration={beat.narration}
        durationInFrames={durationInFrames}
      />
    </AbsoluteFill>
  );
};

export const ShortVideo: React.FC<ShortVideoProps> = ({ part }) => {
  const frame = useCurrentFrame();
  const { fps, durationInFrames } = useVideoConfig();

  let cursor = 0;
  const timedBeats = part.beats.map((beat) => {
    const dur = Math.max(Math.round(fps * 1.8), Math.round(beat.durationSec * fps));
    const start = cursor;
    cursor += dur;
    return { beat, start, durationInFrames: dur };
  });

  const progress = Math.min(1, frame / Math.max(1, durationInFrames - 1));

  return (
    <AbsoluteFill
      style={{
        backgroundColor: THEME.bg,
        fontFamily: THEME.fontSans,
        color: THEME.text,
        overflow: "hidden",
      }}
    >
      {/* Dynamic subtle ambient backdrop */}
      <AbsoluteFill
        style={{
          background:
            "radial-gradient(circle at 15% 12%, rgba(16,185,129,0.20), transparent 44%), radial-gradient(circle at 88% 72%, rgba(56,189,248,0.16), transparent 46%)",
        }}
      />
      <AbsoluteFill
        style={{
          backgroundImage:
            "linear-gradient(rgba(148,163,184,0.045) 1px, transparent 1px), linear-gradient(90deg, rgba(148,163,184,0.045) 1px, transparent 1px)",
          backgroundSize: "56px 56px",
        }}
      />

      {/* Top HUD */}
      <div
        style={{
          position: "absolute",
          top: 64,
          left: 52,
          right: 52,
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          zIndex: 30,
        }}
      >
        <div style={{ display: "flex", alignItems: "center", gap: 12 }}>
          <div
            style={{
              width: 14,
              height: 14,
              borderRadius: 999,
              background: THEME.emerald,
              boxShadow: `0 0 18px ${THEME.emerald}`,
            }}
          />
          <span
            style={{
              fontSize: 28,
              fontWeight: 900,
              letterSpacing: "0.05em",
            }}
          >
            JAVA<span style={{ color: THEME.emeraldLight }}>HUB</span>
          </span>
        </div>

        <div
          style={{
            display: "flex",
            alignItems: "center",
            gap: 10,
            padding: "9px 20px",
            borderRadius: 999,
            background: "rgba(16,185,129,0.15)",
            border: `1.5px solid ${THEME.borderAccent}`,
            fontSize: 23,
            fontWeight: 800,
            color: THEME.emeraldLight,
          }}
        >
          <span>{part.levelLabel}</span>
          <span style={{ opacity: 0.45 }}>•</span>
          <span style={{ color: THEME.text }}>
            Part {part.partNumber}/{part.totalParts}
          </span>
        </div>
      </div>

      {/* Beat sequences */}
      {timedBeats.map(({ beat, start, durationInFrames: beatDur }) => (
        <Sequence key={beat.id} from={start} durationInFrames={beatDur}>
          <BeatRenderer beat={beat} durationInFrames={beatDur} />
        </Sequence>
      ))}

      {/* Segmented progress bar at very top */}
      <div
        style={{
          position: "absolute",
          top: 24,
          left: 52,
          right: 52,
          height: 7,
          borderRadius: 999,
          background: "rgba(148,163,184,0.18)",
          overflow: "hidden",
          zIndex: 40,
        }}
      >
        <div
          style={{
            width: `${progress * 100}%`,
            height: "100%",
            background: `linear-gradient(90deg, ${THEME.emeraldLight}, ${THEME.sky}, ${THEME.amber})`,
          }}
        />
      </div>
    </AbsoluteFill>
  );
};
