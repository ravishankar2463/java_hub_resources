export const THEME = {
  bg: "#090d16",
  bgElevated: "#111827",
  bgCard: "rgba(17, 24, 39, 0.88)",
  bgCode: "#0d1117",
  border: "rgba(148, 163, 184, 0.22)",
  borderAccent: "rgba(16, 185, 129, 0.45)",
  text: "#f8fafc",
  textMuted: "#94a3b8",
  textSoft: "#cbd5e1",
  emerald: "#10b981",
  emeraldLight: "#34d399",
  amber: "#f59e0b",
  sky: "#38bdf8",
  purple: "#a78bfa",
  rose: "#fb7185",
  fontSans:
    '"Inter", "Segoe UI", system-ui, -apple-system, BlinkMacSystemFont, sans-serif',
  fontMono:
    '"JetBrains Mono", "Cascadia Code", "Fira Code", Consolas, monospace',
} as const;

export interface CodeToken {
  text: string;
  color: string;
}

const JAVA_KEYWORDS = new Set([
  "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
  "class", "const", "continue", "default", "do", "double", "else", "enum",
  "extends", "final", "finally", "float", "for", "if", "implements", "import",
  "instanceof", "int", "interface", "long", "native", "new", "non-sealed",
  "null", "package", "permits", "private", "protected", "public", "record",
  "return", "sealed", "short", "static", "strictfp", "super", "switch",
  "synchronized", "this", "throw", "throws", "transient", "try", "var",
  "void", "volatile", "while", "yield", "true", "false",
]);

const TOKEN_REGEX =
  /(\/\/.*$|\/\*[\s\S]*?\*\/|"(?:\\.|[^"\\])*"|'(?:\\.|[^'\\])*'|@[A-Za-z_]\w*|\b\d+(?:_\d+)*(?:\.\d+)?[LlFfDd]?\b|\b[A-Z][A-Za-z0-9_]*\b|\b[a-z_][A-Za-z0-9_]*\b)/gm;

export function tokenizeJavaLine(line: string): CodeToken[] {
  const tokens: CodeToken[] = [];
  let lastIndex = 0;

  for (const match of line.matchAll(TOKEN_REGEX)) {
    const idx = match.index ?? 0;
    if (idx > lastIndex) {
      tokens.push({ text: line.slice(lastIndex, idx), color: "#e2e8f0" });
    }

    const value = match[0];
    let color = "#e2e8f0";
    if (value.startsWith("//") || value.startsWith("/*")) {
      color = "#64748b";
    } else if (value.startsWith('"') || value.startsWith("'")) {
      color = "#86efac";
    } else if (value.startsWith("@")) {
      color = "#fbbf24";
    } else if (/^\d/.test(value)) {
      color = "#f472b6";
    } else if (JAVA_KEYWORDS.has(value)) {
      color = "#c084fc";
    } else if (/^[A-Z]/.test(value)) {
      color = "#38bdf8";
    }

    tokens.push({ text: value, color });
    lastIndex = idx + value.length;
  }

  if (lastIndex < line.length) {
    tokens.push({ text: line.slice(lastIndex), color: "#e2e8f0" });
  }

  return tokens.length > 0 ? tokens : [{ text: " ", color: "#e2e8f0" }];
}
