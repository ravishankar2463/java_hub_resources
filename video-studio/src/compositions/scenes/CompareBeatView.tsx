import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME, tokenizeJavaLine } from "../../theme";
import type { CompareBeat, CompareSide } from "../../types";

const TONE_STYLES = {
  bad: {
    border: THEME.rose,
    bg: "rgba(251, 113, 133, 0.10)",
    badgeBg: "rgba(251, 113, 133, 0.22)",
    badgeText: "#fda4af",
  },
  good: {
    border: THEME.emerald,
    bg: "rgba(16, 185, 129, 0.11)",
    badgeBg: "rgba(16, 185, 129, 0.22)",
    badgeText: THEME.emeraldLight,
  },
  neutral: {
    border: THEME.sky,
    bg: "rgba(56, 189, 248, 0.10)",
    badgeBg: "rgba(56, 189, 248, 0.20)",
    badgeText: "#7dd3fc",
  },
} as const;

const SideCard: React.FC<{ side: CompareSide; anim: number }> = ({ side, anim }) => {
  const tone =
    TONE_STYLES[(side.tone ?? "neutral") as keyof typeof TONE_STYLES] ??
    TONE_STYLES.neutral;
  return (
    <div
      style={{
        borderRadius: 22,
        padding: "26px 28px",
        background: tone.bg,
        border: `2px solid ${tone.border}`,
        opacity: anim,
        transform: `translateY(${interpolate(anim, [0, 1], [24, 0])}px)`,
      }}
    >
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          marginBottom: 16,
          gap: 12,
        }}
      >
        <span style={{ fontSize: 32, fontWeight: 800, color: THEME.text }}>
          {side.label}
        </span>
        <span
          style={{
            padding: "7px 16px",
            borderRadius: 10,
            background: tone.badgeBg,
            color: tone.badgeText,
            fontSize: 22,
            fontWeight: 900,
            letterSpacing: "0.04em",
            textTransform: "uppercase",
          }}
        >
          {side.badge}
        </span>
      </div>

      {side.code ? (
        <div
          style={{
            background: THEME.bgCode,
            borderRadius: 14,
            padding: "18px 20px",
            border: `1px solid ${THEME.border}`,
            fontFamily: THEME.fontMono,
            fontSize: 24,
            lineHeight: 1.48,
            whiteSpace: "pre-wrap",
            wordBreak: "break-word",
            marginBottom: side.points?.length ? 16 : 0,
          }}
        >
          {side.code.split("\n").map((line, i) => (
            <div key={i}>
              {tokenizeJavaLine(line).map((tok, t) => (
                <span key={t} style={{ color: tok.color }}>
                  {tok.text}
                </span>
              ))}
            </div>
          ))}
        </div>
      ) : null}

      {side.points?.length ? (
        <div style={{ display: "flex", flexDirection: "column", gap: 8 }}>
          {side.points.map((pt, i) => (
            <div
              key={i}
              style={{
                fontSize: 27,
                lineHeight: 1.34,
                color: THEME.textSoft,
                fontWeight: 600,
              }}
            >
              • {pt}
            </div>
          ))}
        </div>
      ) : null}
    </div>
  );
};

export const CompareBeatView: React.FC<{
  beat: CompareBeat;
  durationInFrames: number;
}> = ({ beat, durationInFrames }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const topAnim = spring({ frame, fps, config: { damping: 14, stiffness: 160 } });
  const bottomStart = Math.min(24, Math.floor(durationInFrames * 0.35));
  const bottomAnim = spring({
    frame: Math.max(0, frame - bottomStart),
    fps,
    config: { damping: 14, stiffness: 160 },
  });

  return (
    <AbsoluteFill
      style={{
        padding: "165px 52px 425px 52px",
        justifyContent: "flex-start",
      }}
    >
      <h2
        style={{
          margin: "0 0 26px 0",
          fontSize: 48,
          fontWeight: 900,
          color: THEME.text,
          opacity: topAnim,
        }}
      >
        {beat.title}
      </h2>

      <div style={{ display: "flex", flexDirection: "column", gap: 24 }}>
        <SideCard side={beat.top} anim={topAnim} />
        <SideCard side={beat.bottom} anim={bottomAnim} />
      </div>
    </AbsoluteFill>
  );
};
