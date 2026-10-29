package io.javahub.level6.structuralpatterns;

/**
 * Level 6: Structural Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-6/structural-patterns
 */
public class StructuralpatternsLab {

    private final String topicTitle;
    private final int level;

    public StructuralpatternsLab() {
        this.topicTitle = "Structural Patterns";
        this.level = 6;
    }

    public String execute() {
        return "Executing JavaHub Lab for Level " + level + ": " + topicTitle;
    }

    public int getLevel() {
        return level;
    }

    public String getTopicTitle() {
        return topicTitle;
    }
}
