package io.javahub.level7.jpaandhibernatebasics;

/**
 * Level 7: JPA and Hibernate Basics
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/jpa-and-hibernate-basics
 */
public class JpaandhibernatebasicsLab {

    private final String topicTitle;
    private final int level;

    public JpaandhibernatebasicsLab() {
        this.topicTitle = "JPA and Hibernate Basics";
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
