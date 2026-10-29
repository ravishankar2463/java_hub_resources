package io.javahub.level7.hibernateperformancetuning;

/**
 * Level 7: Hibernate Performance Tuning
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/hibernate-performance-tuning
 */
public class HibernateperformancetuningLab {

    private final String topicTitle;
    private final int level;

    public HibernateperformancetuningLab() {
        this.topicTitle = "Hibernate Performance Tuning";
        this.level = 7;
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
