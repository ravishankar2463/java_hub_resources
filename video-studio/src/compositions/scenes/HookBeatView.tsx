import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { THEME } from "../../theme";
import type { HookBeat } from "../../types";

export const HookBeatView: React.FC<{ beat: HookBeat }> = ({ beat }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();

  const badgeSpring = spring({ frame, fps, config: { damping: 14, stiffness: 180 } });
  const slamSpring = spring({ frame: Math.max(0, frame - 3), fps, config: { damping: 11, stiffness: 170 } });
  const subSpring = spring({ frame: Math.max(0, frame - 11), fps, config: { damping: 16 } });

  const scale = interpolate(slamSpring, [0, 1], [1.14, 1]);
  const glowPulse = 0.22 + 0.08 * Math.sin(frame / 6);

  const renderHeadline = () => {
    if (!beat.highlightWord) return beat.headline;
    const idx = beat.headline.toLowerCase().indexOf(beat.highlightWord.toLowerCase());
    if (idx === -1) return beat.headline;
    const before = beat.headline.slice(0, idx);
    const match = beat.headline.slice(idx, idx + beat.highlightWord.length);
    const after = beat.headline.slice(idx + beat.highlightWord.length);
    return (
      <>
        {before}
        <span
          style={{
            color: THEME.bg,
            background: `linear-gradient(135deg, ${THEME.emeraldLight}, ${THEME.sky})`,
            padding: "4px 18px",
            borderRadius: 16,
            display: "inline-block",
          }}
        >
          {match}
        </span>
        {after}
      </>
    );
  };

  return (
    <AbsoluteFill
      style={{
        padding: "210px 68px 430px 56px",
        justifyContent: "center",
        alignItems: "flex-start",
      }}
    >
      {/* Kinetic glow */}
      <div
        style={{
          position: "absolute",
          top: "30%",
          left: "12%",
          width: 680,
          height: 680,
          borderRadius: 999,
          background: `radial-gradient(circle, rgba(16,185,129,${glowPulse}), transparent 65%)`,
          pointerEvents: "none",
        }}
      />

      <div
        style={{
          display: "inline-flex",
          alignItems: "center",
          gap: 12,
          padding: "12px 24px",
          borderRadius: 12,
          background: "rgba(245, 158, 11, 0.16)",
          border: "1.5px solid rgba(245, 158, 11, 0.55)",
          color: THEME.amber,
          fontSize: 28,
          fontWeight: 800,
          letterSpacing: "0.08em",
          textTransform: "uppercase",
          marginBottom: 34,
          opacity: badgeSpring,
          transform: `translateY(${interpolate(badgeSpring, [0, 1], [-24, 0])}px)`,
        }}
      >
        ⚡ {beat.eyebrow}
      </div>

      <h1
        style={{
          margin: 0,
          fontSize: 84,
          fontWeight: 900,
          lineHeight: 1.08,
          letterSpacing: "-0.03em",
          color: THEME.text,
          transform: `scale(${scale})`,
          transformOrigin: "left center",
          opacity: slamSpring,
        }}
      >
        {renderHeadline()}
      </h1>

      <p
        style={{
          marginTop: 38,
          fontSize: 38,
          lineHeight: 1.38,
          fontWeight: 500,
          color: THEME.textSoft,
          maxWidth: 920,
          opacity: subSpring,
          transform: `translateY(${interpolate(subSpring, [0, 1], [28, 0])}px)`,
        }}
      >
        {beat.subline}
      </p>
    </AbsoluteFill>
  );
};
