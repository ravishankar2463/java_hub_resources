package io.javahub.level14.theailandscapeforjavaspringai;

/**
 * Level 14: The AI Landscape for Java: Spring AI
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-14/the-ai-landscape-for-java-spring-ai
 */
public class TheailandscapeforjavaspringaiLab {

    private final String topicTitle;
    private final int level;

    public TheailandscapeforjavaspringaiLab() {
        this.topicTitle = "The AI Landscape for Java: Spring AI";
        this.level = 14;
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
