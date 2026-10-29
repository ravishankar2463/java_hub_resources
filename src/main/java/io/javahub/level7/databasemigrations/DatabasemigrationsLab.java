package io.javahub.level7.databasemigrations;

/**
 * Level 7: Database Migrations
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/database-migrations
 */
public class DatabasemigrationsLab {

    private final String topicTitle;
    private final int level;

    public DatabasemigrationsLab() {
        this.topicTitle = "Database Migrations";
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
