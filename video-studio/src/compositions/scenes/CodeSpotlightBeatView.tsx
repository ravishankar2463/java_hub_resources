import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME, tokenizeJavaLine } from "../../theme";
import type { CodeSpotlightBeat } from "../../types";

const ACCENT_MAP = {
  emerald: { bar: THEME.emerald, bg: "rgba(16,185,129,0.16)", text: THEME.emeraldLight },
  amber: { bar: THEME.amber, bg: "rgba(245,158,11,0.16)", text: "#fcd34d" },
  sky: { bar: THEME.sky, bg: "rgba(56,189,248,0.16)", text: "#7dd3fc" },
  rose: { bar: THEME.rose, bg: "rgba(251,113,133,0.18)", text: "#fda4af" },
} as const;

export const CodeSpotlightBeatView: React.FC<{
  beat: CodeSpotlightBeat;
  durationInFrames: number;
}> = ({ beat, durationInFrames }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const enterAnim = spring({ frame, fps, config: { damping: 15, stiffness: 150 } });
  const lines = beat.code.split("\n");
  const steps = beat.steps.length > 0 ? beat.steps : [];

  // Divide beat duration evenly across spotlight steps
  const activeStepIdx =
    steps.length > 0
      ? Math.min(
          steps.length - 1,
          Math.floor((frame / Math.max(1, durationInFrames - 6)) * steps.length)
        )
      : -1;

  const activeStep = activeStepIdx >= 0 ? steps[activeStepIdx] : null;
  const activeLines = new Set(activeStep?.lines ?? []);
  const accent = ACCENT_MAP[activeStep?.accent ?? "emerald"];

  // Local frame within the active step for snappy callout pop
  const stepSpan = Math.max(1, Math.floor(durationInFrames / Math.max(1, steps.length)));
  const stepLocalFrame = frame - activeStepIdx * stepSpan;
  const calloutAnim = spring({
    frame: Math.max(0, stepLocalFrame),
    fps,
    config: { damping: 14, stiffness: 180 },
  });

  const fontSize = lines.length > 10 ? 25 : 28;

  return (
    <AbsoluteFill
      style={{
        padding: "165px 52px 425px 52px",
        justifyContent: "flex-start",
      }}
    >
      {/* Title */}
      <h2
        style={{
          margin: "0 0 24px 0",
          fontSize: 48,
          fontWeight: 800,
          letterSpacing: "-0.02em",
          color: THEME.text,
          opacity: enterAnim,
          transform: `translateY(${interpolate(enterAnim, [0, 1], [18, 0])}px)`,
        }}
      >
        {beat.title}
      </h2>

      {/* IntelliJ-style IDE window */}
      <div
        style={{
          borderRadius: 22,
          overflow: "hidden",
          background: THEME.bgCode,
          border: `1.5px solid ${THEME.border}`,
          boxShadow: "0 24px 60px rgba(0,0,0,0.55)",
          opacity: enterAnim,
          transform: `scale(${interpolate(enterAnim, [0, 1], [0.97, 1])})`,
        }}
      >
        {/* Tab bar */}
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            padding: "16px 24px",
            background: "#161b22",
            borderBottom: `1px solid ${THEME.border}`,
          }}
        >
          <div style={{ display: "flex", alignItems: "center", gap: 10 }}>
            <span style={{ width: 14, height: 14, borderRadius: 999, background: "#ef4444" }} />
            <span style={{ width: 14, height: 14, borderRadius: 999, background: "#f59e0b" }} />
            <span style={{ width: 14, height: 14, borderRadius: 999, background: "#22c55e" }} />
            <span
              style={{
                marginLeft: 12,
                fontFamily: THEME.fontMono,
                fontSize: 22,
                fontWeight: 700,
                color: THEME.textSoft,
              }}
            >
              {beat.filename}
            </span>
          </div>
          {steps.length > 1 ? (
            <span
              style={{
                fontFamily: THEME.fontMono,
                fontSize: 20,
                color: accent.text,
                fontWeight: 700,
              }}
            >
              {activeStepIdx + 1} / {steps.length}
            </span>
          ) : null}
        </div>

        {/* Code lines with active spotlight */}
        <div
          style={{
            padding: "20px 0",
            fontFamily: THEME.fontMono,
            fontSize,
            lineHeight: 1.54,
          }}
        >
          {lines.map((line, idx) => {
            const lineNum = idx + 1;
            const isHighlighted = activeLines.size === 0 || activeLines.has(lineNum);
            const tokens = tokenizeJavaLine(line);
            return (
              <div
                key={idx}
                style={{
                  display: "flex",
                  gap: 18,
                  padding: "4px 24px 4px 18px",
                  borderLeft: isHighlighted && activeLines.size > 0
                    ? `5px solid ${accent.bar}`
                    : "5px solid transparent",
                  background:
                    isHighlighted && activeLines.size > 0
                      ? accent.bg
                      : "transparent",
                  opacity: isHighlighted ? 1 : 0.3,
                  whiteSpace: "pre-wrap",
                  wordBreak: "break-word",
                }}
              >
                <span
                  style={{
                    minWidth: 34,
                    textAlign: "right",
                    color: isHighlighted ? accent.text : "#475569",
                    fontWeight: isHighlighted ? 700 : 400,
                    userSelect: "none",
                  }}
                >
                  {lineNum}
                </span>
                <span>
                  {tokens.map((tok, tIdx) => (
                    <span key={tIdx} style={{ color: tok.color }}>
                      {tok.text}
                    </span>
                  ))}
                </span>
              </div>
            );
          })}
        </div>
      </div>

      {/* Synchronized Live Callout Card */}
      {activeStep ? (
        <div
          style={{
            marginTop: 28,
            padding: "26px 30px",
            borderRadius: 20,
            background: "rgba(17, 24, 39, 0.94)",
            border: `2px solid ${accent.bar}`,
            boxShadow: `0 14px 40px rgba(0,0,0,0.45)`,
            opacity: calloutAnim,
            transform: `translateY(${interpolate(calloutAnim, [0, 1], [18, 0])}px)`,
          }}
        >
          <div
            style={{
              display: "inline-block",
              padding: "6px 16px",
              borderRadius: 8,
              background: accent.bg,
              color: accent.text,
              fontFamily: THEME.fontMono,
              fontSize: 22,
              fontWeight: 800,
              marginBottom: 12,
            }}
          >
            {activeStep.badge}
          </div>
          <div
            style={{
              fontSize: 31,
              lineHeight: 1.38,
              fontWeight: 600,
              color: THEME.text,
            }}
          >
            {activeStep.explanation}
          </div>
        </div>
      ) : null}
    </AbsoluteFill>
  );
};
