package io.javahub.level11.resiliencypatterns;

/**
 * Level 11: Resiliency Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/resiliency-patterns
 */
public class ResiliencypatternsLab {

    private final String topicTitle;
    private final int level;

    public ResiliencypatternsLab() {
        this.topicTitle = "Resiliency Patterns";
        this.level = 11;
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
