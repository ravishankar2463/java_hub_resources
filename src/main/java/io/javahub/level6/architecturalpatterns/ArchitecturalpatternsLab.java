package io.javahub.level6.architecturalpatterns;

/**
 * Level 6: Architectural Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-6/architectural-patterns
 */
public class ArchitecturalpatternsLab {

    private final String topicTitle;
    private final int level;

    public ArchitecturalpatternsLab() {
        this.topicTitle = "Architectural Patterns";
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
