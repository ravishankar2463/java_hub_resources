import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME } from "../../theme";
import type { CtaBeat } from "../../types";

export const CtaBeatView: React.FC<{ beat: CtaBeat }> = ({ beat }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const headerAnim = spring({ frame, fps, config: { damping: 14 } });

  return (
    <AbsoluteFill
      style={{
        padding: "175px 56px 425px 56px",
        justifyContent: "center",
      }}
    >
      <h2
        style={{
          margin: "0 0 32px 0",
          fontSize: 54,
          fontWeight: 900,
          color: THEME.text,
          opacity: headerAnim,
          transform: `translateY(${interpolate(headerAnim, [0, 1], [24, 0])}px)`,
        }}
      >
        {beat.title}
      </h2>

      <div style={{ display: "flex", flexDirection: "column", gap: 18, marginBottom: 36 }}>
        {beat.takeaways.map((item, idx) => {
          const itemAnim = spring({
            frame: Math.max(0, frame - 5 - idx * 6),
            fps,
            config: { damping: 15 },
          });
          return (
            <div
              key={idx}
              style={{
                display: "flex",
                alignItems: "center",
                gap: 18,
                padding: "22px 26px",
                borderRadius: 18,
                background: "rgba(17, 24, 39, 0.9)",
                border: `1.5px solid ${THEME.borderAccent}`,
                opacity: itemAnim,
                transform: `translateX(${interpolate(itemAnim, [0, 1], [-24, 0])}px)`,
              }}
            >
              <span
                style={{
                  fontSize: 30,
                  color: THEME.emeraldLight,
                  fontWeight: 900,
                }}
              >
                ✓
              </span>
              <span style={{ fontSize: 30, fontWeight: 600, color: THEME.text }}>
                {item}
              </span>
            </div>
          );
        })}
      </div>

      {beat.command ? (
        <div
          style={{
            padding: "24px 26px",
            borderRadius: 18,
            background: THEME.bgCode,
            border: `1.5px solid ${THEME.border}`,
            marginBottom: beat.nextPartTeaser ? 24 : 0,
          }}
        >
          <div
            style={{
              fontSize: 20,
              color: THEME.textMuted,
              textTransform: "uppercase",
              letterSpacing: "0.08em",
              fontWeight: 700,
              marginBottom: 10,
            }}
          >
            Hands-On Java 21 Lab
          </div>
          <div
            style={{
              fontFamily: THEME.fontMono,
              fontSize: 24,
              color: THEME.emeraldLight,
              fontWeight: 700,
              wordBreak: "break-all",
            }}
          >
            $ {beat.command}
          </div>
        </div>
      ) : null}

      {beat.nextPartTeaser ? (
        <div
          style={{
            padding: "22px 28px",
            borderRadius: 18,
            background: "linear-gradient(135deg, rgba(16,185,129,0.22), rgba(56,189,248,0.18))",
            border: `2px solid ${THEME.emeraldLight}`,
            fontSize: 30,
            fontWeight: 800,
            color: THEME.text,
            textAlign: "center",
          }}
        >
          {beat.nextPartTeaser}
        </div>
      ) : null}
    </AbsoluteFill>
  );
};
