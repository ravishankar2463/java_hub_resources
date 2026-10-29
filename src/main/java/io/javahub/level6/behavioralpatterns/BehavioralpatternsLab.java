package io.javahub.level6.behavioralpatterns;

/**
 * Level 6: Behavioral Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-6/behavioral-patterns
 */
public class BehavioralpatternsLab {

    private final String topicTitle;
    private final int level;

    public BehavioralpatternsLab() {
        this.topicTitle = "Behavioral Patterns";
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
