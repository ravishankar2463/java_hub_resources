package io.javahub.level8.springeventscachingscheduling;

/**
 * Level 8: Spring Events, Caching, & Scheduling
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/spring-events-caching-scheduling
 */
public class SpringeventscachingschedulingLab {

    private final String topicTitle;
    private final int level;

    public SpringeventscachingschedulingLab() {
        this.topicTitle = "Spring Events, Caching, & Scheduling";
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
