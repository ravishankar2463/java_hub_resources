package io.javahub.level1.memoryreferencescopying;

/**
 * Level 1: Memory References & Copying
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/memory-references-copying
 */
public class MemoryreferencescopyingLab {

    private final String topicTitle;
    private final int level;

    public MemoryreferencescopyingLab() {
        this.topicTitle = "Memory References & Copying";
        this.level = 1;
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
