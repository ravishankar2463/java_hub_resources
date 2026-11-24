import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME } from "../../theme";
import type { TerminalBeat } from "../../types";

export const TerminalBeatView: React.FC<{
  beat: TerminalBeat;
  durationInFrames: number;
}> = ({ beat, durationInFrames }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const enterAnim = spring({ frame, fps, config: { damping: 15 } });
  const totalLines = beat.lines.length;
  const revealSpan = Math.max(24, durationInFrames * 0.72);
  const cursorVisible = Math.floor(frame / 10) % 2 === 0;

  return (
    <AbsoluteFill
      style={{
        padding: "165px 50px 425px 50px",
        justifyContent: "flex-start",
      }}
    >
      <h2
        style={{
          margin: "0 0 12px 0",
          fontSize: 48,
          fontWeight: 900,
          color: THEME.text,
          opacity: enterAnim,
        }}
      >
        {beat.title}
      </h2>
      {beat.caption ? (
        <div
          style={{
            marginBottom: 24,
            fontSize: 27,
            color: THEME.amber,
            fontWeight: 600,
            opacity: enterAnim,
          }}
        >
          {beat.caption}
        </div>
      ) : null}

      <div
        style={{
          borderRadius: 22,
          overflow: "hidden",
          background: "#05080f",
          border: `1.5px solid ${THEME.borderAccent}`,
          boxShadow: "0 24px 64px rgba(0,0,0,0.65)",
          opacity: enterAnim,
          transform: `scale(${interpolate(enterAnim, [0, 1], [0.96, 1])})`,
        }}
      >
        {/* Terminal chrome */}
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            padding: "16px 24px",
            background: "#111827",
            borderBottom: `1px solid ${THEME.border}`,
          }}
        >
          <div style={{ display: "flex", gap: 10 }}>
            <span style={{ width: 14, height: 14, borderRadius: 999, background: "#ef4444" }} />
            <span style={{ width: 14, height: 14, borderRadius: 999, background: "#f59e0b" }} />
            <span style={{ width: 14, height: 14, borderRadius: 999, background: "#22c55e" }} />
          </div>
          <span
            style={{
              fontFamily: THEME.fontMono,
              fontSize: 21,
              color: THEME.textMuted,
            }}
          >
            bash — javahub@dev:~/project
          </span>
        </div>

        {/* Lines */}
        <div
          style={{
            padding: "28px 26px",
            fontFamily: THEME.fontMono,
            fontSize: totalLines > 8 ? 23 : 26,
            lineHeight: 1.56,
            display: "flex",
            flexDirection: "column",
            gap: 10,
          }}
        >
          {beat.lines.map((line, idx) => {
            const startFrame = Math.floor((idx / Math.max(1, totalLines)) * revealSpan);
            const localFrame = frame - startFrame;
            if (localFrame < 0) return null;

            const isCmd = line.kind === "command";
            const typedText = isCmd
              ? line.text.slice(0, Math.max(1, Math.floor(localFrame * 2.2)))
              : line.text;
            const isTyping = isCmd && typedText.length < line.text.length;

            let color: string = THEME.textSoft;
            if (line.kind === "command") color = THEME.text;
            if (line.kind === "success") color = THEME.emeraldLight;
            if (line.kind === "error") color = THEME.rose;
            if (line.kind === "comment") color = "#64748b";

            return (
              <div
                key={idx}
                style={{
                  color,
                  fontWeight: isCmd || line.kind === "success" ? 700 : 500,
                  whiteSpace: "pre-wrap",
                  wordBreak: "break-word",
                }}
              >
                {isCmd ? (
                  <span style={{ color: THEME.emeraldLight, marginRight: 12 }}>$</span>
                ) : null}
                {typedText}
                {isTyping && cursorVisible ? (
                  <span
                    style={{
                      display: "inline-block",
                      width: 14,
                      height: 26,
                      background: THEME.emeraldLight,
                      marginLeft: 4,
                      verticalAlign: "middle",
                    }}
                  />
                ) : null}
              </div>
            );
          })}
        </div>
      </div>
    </AbsoluteFill>
  );
};
