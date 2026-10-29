package io.javahub.level1.classesandobjects;

/**
 * Level 1: Classes and Objects
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/classes-and-objects
 */
public class ClassesandobjectsLab {

    private final String topicTitle;
    private final int level;

    public ClassesandobjectsLab() {
        this.topicTitle = "Classes and Objects";
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
