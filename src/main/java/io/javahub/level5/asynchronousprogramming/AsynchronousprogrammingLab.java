package io.javahub.level5.asynchronousprogramming;

/**
 * Level 5: Asynchronous Programming
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-5/asynchronous-programming
 */
public class AsynchronousprogrammingLab {

    private final String topicTitle;
    private final int level;

    public AsynchronousprogrammingLab() {
        this.topicTitle = "Asynchronous Programming";
        this.level = 5;
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
