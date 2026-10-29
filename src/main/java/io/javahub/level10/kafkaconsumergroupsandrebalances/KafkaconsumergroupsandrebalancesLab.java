package io.javahub.level10.kafkaconsumergroupsandrebalances;

/**
 * Level 10: Kafka Consumer Groups and Rebalances
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/kafka-consumer-groups-and-rebalances
 */
public class KafkaconsumergroupsandrebalancesLab {

    private final String topicTitle;
    private final int level;

    public KafkaconsumergroupsandrebalancesLab() {
        this.topicTitle = "Kafka Consumer Groups and Rebalances";
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
