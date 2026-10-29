package io.javahub.level9.springsecurityarchitecture;

/**
 * Level 9: Spring Security Architecture
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/spring-security-architecture
 */
public class SpringsecurityarchitectureLab {

    private final String topicTitle;
    private final int level;

    public SpringsecurityarchitectureLab() {
        this.topicTitle = "Spring Security Architecture";
        this.level = 9;
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
