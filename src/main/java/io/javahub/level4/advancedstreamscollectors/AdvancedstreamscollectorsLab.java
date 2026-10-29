package io.javahub.level4.advancedstreamscollectors;

/**
 * Level 4: Advanced Streams & Collectors
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-4/advanced-streams-collectors
 */
public class AdvancedstreamscollectorsLab {

    private final String topicTitle;
    private final int level;

    public AdvancedstreamscollectorsLab() {
        this.topicTitle = "Advanced Streams & Collectors";
        this.level = 4;
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
