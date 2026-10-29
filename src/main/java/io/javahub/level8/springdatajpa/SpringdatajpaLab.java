package io.javahub.level8.springdatajpa;

/**
 * Level 8: Spring Data JPA
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/spring-data-jpa
 */
public class SpringdatajpaLab {

    private final String topicTitle;
    private final int level;

    public SpringdatajpaLab() {
        this.topicTitle = "Spring Data JPA";
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
