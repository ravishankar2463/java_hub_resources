package io.javahub.level1.sealedclassesandpatternmatching;

/**
 * Level 1: Sealed Classes and Pattern Matching
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/sealed-classes-and-pattern-matching
 */
public class SealedclassesandpatternmatchingLab {

    private final String topicTitle;
    private final int level;

    public SealedclassesandpatternmatchingLab() {
        this.topicTitle = "Sealed Classes and Pattern Matching";
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
