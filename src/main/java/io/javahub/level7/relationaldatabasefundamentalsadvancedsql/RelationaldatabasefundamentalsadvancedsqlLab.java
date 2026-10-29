package io.javahub.level7.relationaldatabasefundamentalsadvancedsql;

/**
 * Level 7: Relational Database Fundamentals & Advanced SQL
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/relational-database-fundamentals-advanced-sql
 */
public class RelationaldatabasefundamentalsadvancedsqlLab {

    private final String topicTitle;
    private final int level;

    public RelationaldatabasefundamentalsadvancedsqlLab() {
        this.topicTitle = "Relational Database Fundamentals & Advanced SQL";
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
