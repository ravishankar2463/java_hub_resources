package io.javahub.level0.introtooops;

/**
 * Level 0: Intro to OOPS
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-0/intro-to-oops
 */
public class IntrotooopsLab {

    private final String topicTitle;
    private final int level;

    public IntrotooopsLab() {
        this.topicTitle = "Intro to OOPS";
        this.level = 0;
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
