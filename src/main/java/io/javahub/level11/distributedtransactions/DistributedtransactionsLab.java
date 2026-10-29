package io.javahub.level11.distributedtransactions;

/**
 * Level 11: Distributed Transactions
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/distributed-transactions
 */
public class DistributedtransactionsLab {

    private final String topicTitle;
    private final int level;

    public DistributedtransactionsLab() {
        this.topicTitle = "Distributed Transactions";
        this.level = 11;
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
