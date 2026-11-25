export type VideoFormat = "shorts" | "landscape";

export type SfxKind = "none" | "whoosh" | "pop" | "keyclick" | "success" | "error";

export interface CodeHighlightStep {
  /** 1-based line numbers to highlight */
  lines: number[];
  badge: string;
  explanation: string;
  accent?: "emerald" | "amber" | "sky" | "rose";
}

export interface FlowNode {
  label: string;
  detail: string;
  tag?: string;
  accent?: "emerald" | "amber" | "sky" | "purple" | "rose";
}

export interface TerminalLine {
  text: string;
  kind: "command" | "output" | "success" | "error" | "comment";
}

export interface CompareSide {
  label: string;
  badge: string;
  tone: "bad" | "good" | "neutral";
  code?: string;
  points?: string[];
}

export interface BaseBeat {
  id: string;
  narration: string;
  /** Optional spoken-only override for TTS (captions still display `narration`) */
  ttsNarration?: string;
  /** Relative path inside video-studio/public/ (auto-filled by TTS or .human.wav override) */
  audioFile?: string;
  /** Exact duration in seconds (measured from WAV + tight breath padding) */
  durationSec: number;
  sfx?: SfxKind;
}

export interface HookBeat extends BaseBeat {
  kind: "hook";
  eyebrow: string;
  headline: string;
  highlightWord?: string;
  subline: string;
}

export interface CodeSpotlightBeat extends BaseBeat {
  kind: "code-spotlight";
  title: string;
  filename: string;
  code: string;
  steps: CodeHighlightStep[];
}

export interface FlowDiagramBeat extends BaseBeat {
  kind: "flow-diagram";
  title: string;
  subtitle?: string;
  nodes: FlowNode[];
}

export interface TerminalBeat extends BaseBeat {
  kind: "terminal";
  title: string;
  caption?: string;
  lines: TerminalLine[];
}

export interface CompareBeat extends BaseBeat {
  kind: "compare";
  title: string;
  top: CompareSide;
  bottom: CompareSide;
}

export interface CtaBeat extends BaseBeat {
  kind: "cta";
  title: string;
  takeaways: string[];
  command?: string;
  nextPartTeaser?: string;
}

export type StoryboardBeat =
  | HookBeat
  | CodeSpotlightBeat
  | FlowDiagramBeat
  | TerminalBeat
  | CompareBeat
  | CtaBeat;

export interface PartStoryboard {
  partNumber: number;
  totalParts: number;
  partSlug: string;
  partTitle: string;
  chapterId: string;
  level: string;
  levelLabel: string;
  slug: string;
  chapterTitle: string;
  roadmapUrl: string;
  branchName: string;
  fps: number;
  voice?: string;
  speed?: number;
  beats: StoryboardBeat[];
}

export interface ChapterStoryboardBundle {
  chapterId: string;
  level: string;
  levelLabel: string;
  slug: string;
  chapterTitle: string;
  roadmapUrl: string;
  branchName: string;
  voice?: string;
  speed?: number;
  parts: Array<{
    partNumber: number;
    partSlug: string;
    partTitle: string;
    beats: StoryboardBeat[];
  }>;
}

export interface ShortVideoProps extends Record<string, unknown> {
  part: PartStoryboard;
  format: VideoFormat;
}

export interface VideoManifestEntry {
  chapterId: string;
  level: string;
  slug: string;
  chapterTitle: string;
  partNumber: number;
  totalParts: number;
  partSlug: string;
  partTitle: string;
  format: VideoFormat;
  durationSec: number;
  file: string;
  videoUrl: string;
  releaseTag?: string;
  youtubeId?: string;
  youtubeUrl?: string;
  youtubeStatus?: "unlisted" | "public" | "private";
  scheduledPublishAt?: string;
  publishedAt?: string;
  roadmapUrl: string;
  branchName: string;
  renderedAt: string;
}
