import fs from "node:fs";
import path from "node:path";

export function listChapters(hubDir: string): string[] {
  const roadmapRoot = path.join(hubDir, "content", "roadmap");
  if (!fs.existsSync(roadmapRoot)) {
    throw new Error(`Roadmap content directory not found: ${roadmapRoot}`);
  }

  const levels = fs
    .readdirSync(roadmapRoot, { withFileTypes: true })
    .filter((d) => d.isDirectory() && /^level-\d+$/i.test(d.name))
    .sort((a, b) => {
      const numA = Number(a.name.replace(/\D/g, ""));
      const numB = Number(b.name.replace(/\D/g, ""));
      return numA - numB;
    });

  const chapters: string[] = [];
  for (const level of levels) {
    const levelDir = path.join(roadmapRoot, level.name);
    const files = fs
      .readdirSync(levelDir)
      .filter((f) => f.endsWith(".md"))
      .sort();
    for (const file of files) {
      chapters.push(`${level.name}/${file.replace(/\.md$/i, "")}`);
    }
  }
  return chapters;
}
