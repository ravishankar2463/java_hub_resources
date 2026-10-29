package io.javahub.level15.ultralowlatencymessagingaeronandchroniclequeue;

/**
 * Level 15: Ultra-Low-Latency Messaging: Aeron and Chronicle Queue
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-15/ultra-low-latency-messaging-aeron-and-chronicle-queue
 */
public class UltralowlatencymessagingaeronandchroniclequeueLab {

    private final String topicTitle;
    private final int level;

    public UltralowlatencymessagingaeronandchroniclequeueLab() {
        this.topicTitle = "Ultra-Low-Latency Messaging: Aeron and Chronicle Queue";
        this.level = 15;
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
