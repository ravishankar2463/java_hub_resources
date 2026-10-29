package io.javahub.level7.nosqldatabases;

/**
 * Level 7: NoSQL Databases
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/nosql-databases
 */
public class NosqldatabasesLab {

    private final String topicTitle;
    private final int level;

    public NosqldatabasesLab() {
        this.topicTitle = "NoSQL Databases";
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
