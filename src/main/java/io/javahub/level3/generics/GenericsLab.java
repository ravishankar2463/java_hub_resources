package io.javahub.level3.generics;

/**
 * Level 3: Generics
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-3/generics
 */
public class GenericsLab {

    private final String topicTitle;
    private final int level;

    public GenericsLab() {
        this.topicTitle = "Generics";
        this.level = 3;
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
