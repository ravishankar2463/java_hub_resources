package io.javahub.level6.solidprinciplesbasics;

/**
 * Level 6: SOLID Principles Basics
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-6/solid-principles-basics
 */
public class SolidprinciplesbasicsLab {

    private final String topicTitle;
    private final int level;

    public SolidprinciplesbasicsLab() {
        this.topicTitle = "SOLID Principles Basics";
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
