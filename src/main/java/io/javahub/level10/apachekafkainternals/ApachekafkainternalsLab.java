package io.javahub.level10.apachekafkainternals;

/**
 * Level 10: Apache Kafka Internals
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/apache-kafka-internals
 */
public class ApachekafkainternalsLab {

    private final String topicTitle;
    private final int level;

    public ApachekafkainternalsLab() {
        this.topicTitle = "Apache Kafka Internals";
        this.level = 10;
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
