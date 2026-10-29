package io.javahub.level7.jdbcandconnectionpooling;

/**
 * Level 7: JDBC and Connection Pooling
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/jdbc-and-connection-pooling
 */
public class JdbcandconnectionpoolingLab {

    private final String topicTitle;
    private final int level;

    public JdbcandconnectionpoolingLab() {
        this.topicTitle = "JDBC and Connection Pooling";
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
