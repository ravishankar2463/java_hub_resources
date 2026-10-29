package io.javahub.level1.polymorphism;

/**
 * Level 1: Polymorphism
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/polymorphism
 */
public class PolymorphismLab {

    private final String topicTitle;
    private final int level;

    public PolymorphismLab() {
        this.topicTitle = "Polymorphism";
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
