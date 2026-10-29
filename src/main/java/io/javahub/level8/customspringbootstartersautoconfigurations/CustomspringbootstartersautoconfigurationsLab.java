package io.javahub.level8.customspringbootstartersautoconfigurations;

/**
 * Level 8: Custom Spring Boot Starters & Auto-Configurations
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/custom-spring-boot-starters-auto-configurations
 */
public class CustomspringbootstartersautoconfigurationsLab {

    private final String topicTitle;
    private final int level;

    public CustomspringbootstartersautoconfigurationsLab() {
        this.topicTitle = "Custom Spring Boot Starters & Auto-Configurations";
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
