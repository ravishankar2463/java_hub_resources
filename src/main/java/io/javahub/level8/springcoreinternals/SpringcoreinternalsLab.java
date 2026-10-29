package io.javahub.level8.springcoreinternals;

/**
 * Level 8: Spring Core Internals
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/spring-core-internals
 */
public class SpringcoreinternalsLab {

    private final String topicTitle;
    private final int level;

    public SpringcoreinternalsLab() {
        this.topicTitle = "Spring Core Internals";
        this.level = 8;
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
