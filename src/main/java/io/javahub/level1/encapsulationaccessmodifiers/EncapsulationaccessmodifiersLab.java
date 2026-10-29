package io.javahub.level1.encapsulationaccessmodifiers;

/**
 * Level 1: Encapsulation & Access Modifiers
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/encapsulation-access-modifiers
 */
public class EncapsulationaccessmodifiersLab {

    private final String topicTitle;
    private final int level;

    public EncapsulationaccessmodifiersLab() {
        this.topicTitle = "Encapsulation & Access Modifiers";
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
