import React from "react";
import { Composition } from "remotion";
import { ShortVideo } from "./compositions/ShortVideo";
import type { PartStoryboard, ShortVideoProps } from "./types";

const SAMPLE_PART: PartStoryboard = {
  partNumber: 1,
  totalParts: 3,
  partSlug: "part-1-hello-world-anatomy",
  partTitle: "Why Java Needs 6 Lines for Hello World",
  chapterId: "level-0/anatomy-of-a-java-program",
  level: "level-0",
  levelLabel: "Level 0",
  slug: "anatomy-of-a-java-program",
  chapterTitle: "Anatomy of a Java Program",
  roadmapUrl:
    "https://ravishankar2463.github.io/java_hub/roadmap/level-0/anatomy-of-a-java-program",
  branchName: "topic/level-0/anatomy-of-a-java-program",
  fps: 30,
  beats: [
    {
      id: "beat-0-hook",
      kind: "hook",
      eyebrow: "Java Fundamentals",
      headline: "Why does Java need 6 lines for Hello World?",
      highlightWord: "6 lines",
      subline: "It's not boilerplate—it's a blueprint built for massive teams.",
      narration:
        "Python prints Hello World in one line. Java takes six. It's not random bloat—every single keyword has a job.",
      durationSec: 5.5,
      sfx: "pop",
    },
  ],
};

function computeTotalFrames(part: PartStoryboard): number {
  const fps = part.fps || 30;
  const totalSec = part.beats.reduce(
    (sum, b) => sum + Math.max(1.8, b.durationSec),
    0
  );
  return Math.max(fps * 3, Math.ceil(totalSec * fps));
}

export const RemotionRoot: React.FC = () => {
  return (
    <Composition
      id="JavaHubShorts"
      component={ShortVideo}
      width={1080}
      height={1920}
      fps={30}
      durationInFrames={computeTotalFrames(SAMPLE_PART)}
      defaultProps={
        {
          part: SAMPLE_PART,
          format: "shorts",
        } satisfies ShortVideoProps
      }
      calculateMetadata={({ props }) => {
        const part = props.part;
        return {
          fps: part.fps || 30,
          durationInFrames: computeTotalFrames(part),
        };
      }}
    />
  );
};
