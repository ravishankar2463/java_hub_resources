package io.javahub.level1.thestatickeyword;

/**
 * Level 1: The Static Keyword
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-1/the-static-keyword
 */
public class ThestatickeywordLab {

    private final String topicTitle;
    private final int level;

    public ThestatickeywordLab() {
        this.topicTitle = "The Static Keyword";
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
