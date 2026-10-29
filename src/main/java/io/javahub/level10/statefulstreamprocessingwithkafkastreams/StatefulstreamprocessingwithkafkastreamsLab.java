package io.javahub.level10.statefulstreamprocessingwithkafkastreams;

/**
 * Level 10: Stateful Stream Processing with Kafka Streams
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/stateful-stream-processing-with-kafka-streams
 */
public class StatefulstreamprocessingwithkafkastreamsLab {

    private final String topicTitle;
    private final int level;

    public StatefulstreamprocessingwithkafkastreamsLab() {
        this.topicTitle = "Stateful Stream Processing with Kafka Streams";
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
