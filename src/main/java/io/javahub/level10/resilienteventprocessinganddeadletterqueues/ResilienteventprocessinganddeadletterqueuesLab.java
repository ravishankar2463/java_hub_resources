package io.javahub.level10.resilienteventprocessinganddeadletterqueues;

/**
 * Level 10: Resilient Event Processing and Dead Letter Queues
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/resilient-event-processing-and-dead-letter-queues
 */
public class ResilienteventprocessinganddeadletterqueuesLab {

    private final String topicTitle;
    private final int level;

    public ResilienteventprocessinganddeadletterqueuesLab() {
        this.topicTitle = "Resilient Event Processing and Dead Letter Queues";
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
