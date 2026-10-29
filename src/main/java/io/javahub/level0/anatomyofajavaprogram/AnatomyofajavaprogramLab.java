package io.javahub.level0.anatomyofajavaprogram;

/**
 * Level 0: Anatomy of a Java Program
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-0/anatomy-of-a-java-program
 */
public class AnatomyofajavaprogramLab {

    private final String topicTitle;
    private final int level;

    public AnatomyofajavaprogramLab() {
        this.topicTitle = "Anatomy of a Java Program";
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
