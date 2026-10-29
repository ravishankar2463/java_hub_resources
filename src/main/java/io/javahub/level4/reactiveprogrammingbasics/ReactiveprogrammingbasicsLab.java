package io.javahub.level4.reactiveprogrammingbasics;

/**
 * Level 4: Reactive Programming Basics
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-4/reactive-programming-basics
 */
public class ReactiveprogrammingbasicsLab {

    private final String topicTitle;
    private final int level;

    public ReactiveprogrammingbasicsLab() {
        this.topicTitle = "Reactive Programming Basics";
        this.level = 4;
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
