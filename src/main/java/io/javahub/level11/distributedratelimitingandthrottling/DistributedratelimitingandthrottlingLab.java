package io.javahub.level11.distributedratelimitingandthrottling;

/**
 * Level 11: Distributed Rate Limiting and Throttling
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/distributed-rate-limiting-and-throttling
 */
public class DistributedratelimitingandthrottlingLab {

    private final String topicTitle;
    private final int level;

    public DistributedratelimitingandthrottlingLab() {
        this.topicTitle = "Distributed Rate Limiting and Throttling";
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
