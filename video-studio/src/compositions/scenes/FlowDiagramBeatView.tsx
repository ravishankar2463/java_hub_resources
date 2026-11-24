import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME } from "../../theme";
import type { FlowDiagramBeat } from "../../types";

const NODE_ACCENTS = {
  emerald: { border: THEME.emerald, bg: "rgba(16,185,129,0.16)", text: THEME.emeraldLight },
  sky: { border: THEME.sky, bg: "rgba(56,189,248,0.16)", text: "#7dd3fc" },
  amber: { border: THEME.amber, bg: "rgba(245,158,11,0.16)", text: "#fcd34d" },
  purple: { border: THEME.purple, bg: "rgba(167,139,250,0.18)", text: "#c4b5fd" },
  rose: { border: THEME.rose, bg: "rgba(251,113,133,0.18)", text: "#fda4af" },
} as const;

export const FlowDiagramBeatView: React.FC<{
  beat: FlowDiagramBeat;
  durationInFrames: number;
}> = ({ beat, durationInFrames }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const headerAnim = spring({ frame, fps, config: { damping: 15 } });
  const nodes = beat.nodes;
  const activeIdx = Math.min(
    nodes.length - 1,
    Math.floor((frame / Math.max(1, durationInFrames - 8)) * nodes.length)
  );

  const compact = nodes.length >= 5;

  return (
    <AbsoluteFill
      style={{
        padding: "165px 56px 425px 56px",
        justifyContent: "flex-start",
      }}
    >
      <div style={{ opacity: headerAnim, marginBottom: compact ? 24 : 34 }}>
        <h2
          style={{
            margin: 0,
            fontSize: 48,
            fontWeight: 900,
            letterSpacing: "-0.02em",
            color: THEME.text,
          }}
        >
          {beat.title}
        </h2>
        {beat.subtitle ? (
          <div style={{ marginTop: 8, fontSize: 27, color: THEME.textMuted, fontWeight: 500 }}>
            {beat.subtitle}
          </div>
        ) : null}
      </div>

      {/* Vertical Animated Architecture Flow */}
      <div style={{ display: "flex", flexDirection: "column", alignItems: "stretch" }}>
        {nodes.map((node, idx) => {
          const isReached = idx <= activeIdx;
          const isCurrent = idx === activeIdx;
          const colors = NODE_ACCENTS[node.accent ?? "emerald"];
          const revealStart = Math.floor((idx / Math.max(1, nodes.length)) * (durationInFrames * 0.72));
          const pop = spring({
            frame: Math.max(0, frame - Math.min(revealStart, idx * 8)),
            fps,
            config: { damping: 14, stiffness: 160 },
          });

          return (
            <React.Fragment key={idx}>
              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "space-between",
                  gap: 20,
                  padding: compact ? "20px 26px" : "26px 30px",
                  borderRadius: 20,
                  background: isCurrent ? colors.bg : "rgba(17, 24, 39, 0.88)",
                  border: `2px solid ${isCurrent ? colors.border : isReached ? "rgba(148,163,184,0.34)" : "rgba(148,163,184,0.14)"}`,
                  boxShadow: isCurrent ? `0 0 34px ${colors.bg}` : "none",
                  opacity: interpolate(pop, [0, 1], [0.25, isReached ? 1 : 0.45]),
                  transform: `scale(${isCurrent ? 1.02 : 1}) translateY(${interpolate(pop, [0, 1], [16, 0])}px)`,
                }}
              >
                <div style={{ display: "flex", alignItems: "center", gap: 20, flex: 1 }}>
                  <div
                    style={{
                      minWidth: 46,
                      height: 46,
                      borderRadius: 14,
                      background: isReached ? colors.bg : "rgba(30,41,59,0.8)",
                      border: `1.5px solid ${isReached ? colors.border : THEME.border}`,
                      color: isReached ? colors.text : THEME.textMuted,
                      display: "flex",
                      alignItems: "center",
                      justifyContent: "center",
                      fontSize: 24,
                      fontWeight: 900,
                      fontFamily: THEME.fontMono,
                    }}
                  >
                    {idx + 1}
                  </div>
                  <div style={{ flex: 1 }}>
                    <div
                      style={{
                        fontSize: compact ? 31 : 34,
                        fontWeight: 800,
                        color: THEME.text,
                        lineHeight: 1.2,
                      }}
                    >
                      {node.label}
                    </div>
                    <div
                      style={{
                        marginTop: 5,
                        fontSize: compact ? 24 : 26,
                        color: isCurrent ? THEME.text : THEME.textSoft,
                        lineHeight: 1.32,
                      }}
                    >
                      {node.detail}
                    </div>
                  </div>
                </div>

                {node.tag ? (
                  <div
                    style={{
                      padding: "8px 16px",
                      borderRadius: 10,
                      background: "rgba(9,13,22,0.75)",
                      border: `1px solid ${colors.border}`,
                      color: colors.text,
                      fontFamily: THEME.fontMono,
                      fontSize: 21,
                      fontWeight: 700,
                      whiteSpace: "nowrap",
                    }}
                  >
                    {node.tag}
                  </div>
                ) : null}
              </div>

              {/* Animated Connector Arrow */}
              {idx < nodes.length - 1 ? (
                <div
                  style={{
                    display: "flex",
                    justifyContent: "center",
                    height: compact ? 30 : 40,
                    alignItems: "center",
                    opacity: idx < activeIdx ? 1 : 0.25,
                  }}
                >
                  <svg width="32" height="28" viewBox="0 0 32 28">
                    <path
                      d="M16 2 L16 22 M8 15 L16 24 L24 15"
                      stroke={idx < activeIdx ? colors.border : "#475569"}
                      strokeWidth="3.5"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      fill="none"
                    />
                  </svg>
                </div>
              ) : null}
            </React.Fragment>
          );
        })}
      </div>
    </AbsoluteFill>
  );
};
