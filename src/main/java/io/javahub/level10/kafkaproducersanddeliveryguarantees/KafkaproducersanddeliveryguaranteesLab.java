package io.javahub.level10.kafkaproducersanddeliveryguarantees;

/**
 * Level 10: Kafka Producers and Delivery Guarantees
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/kafka-producers-and-delivery-guarantees
 */
public class KafkaproducersanddeliveryguaranteesLab {

    private final String topicTitle;
    private final int level;

    public KafkaproducersanddeliveryguaranteesLab() {
        this.topicTitle = "Kafka Producers and Delivery Guarantees";
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
