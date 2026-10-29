package io.javahub.level1.abstraction;

/**
 * Level 1: Abstraction
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/abstraction
 */
public class AbstractionLab {

    private final String topicTitle;
    private final int level;

    public AbstractionLab() {
        this.topicTitle = "Abstraction";
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
