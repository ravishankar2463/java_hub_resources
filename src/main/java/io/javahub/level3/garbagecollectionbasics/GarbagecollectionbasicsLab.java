package io.javahub.level3.garbagecollectionbasics;

/**
 * Level 3: Garbage Collection Basics
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-3/garbage-collection-basics
 */
public class GarbagecollectionbasicsLab {

    private final String topicTitle;
    private final int level;

    public GarbagecollectionbasicsLab() {
        this.topicTitle = "Garbage Collection Basics";
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
