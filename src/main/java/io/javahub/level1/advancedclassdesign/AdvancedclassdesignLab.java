package io.javahub.level1.advancedclassdesign;

/**
 * Level 1: Advanced Class Design
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/advanced-class-design
 */
public class AdvancedclassdesignLab {

    private final String topicTitle;
    private final int level;

    public AdvancedclassdesignLab() {
        this.topicTitle = "Advanced Class Design";
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
