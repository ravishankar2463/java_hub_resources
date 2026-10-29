package io.javahub.level5.thejavamemorymodeljmm;

/**
 * Level 5: The Java Memory Model (JMM)
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-5/the-java-memory-model-jmm
 */
public class ThejavamemorymodeljmmLab {

    private final String topicTitle;
    private final int level;

    public ThejavamemorymodeljmmLab() {
        this.topicTitle = "The Java Memory Model (JMM)";
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
