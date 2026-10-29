package io.javahub.level1.compositionoverinheritance;

/**
 * Level 1: Composition over Inheritance
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/composition-over-inheritance
 */
public class CompositionoverinheritanceLab {

    private final String topicTitle;
    private final int level;

    public CompositionoverinheritanceLab() {
        this.topicTitle = "Composition over Inheritance";
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
