package io.javahub.level1.inheritance;

/**
 * Level 1: Inheritance
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/inheritance
 */
public class InheritanceLab {

    private final String topicTitle;
    private final int level;

    public InheritanceLab() {
        this.topicTitle = "Inheritance";
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
