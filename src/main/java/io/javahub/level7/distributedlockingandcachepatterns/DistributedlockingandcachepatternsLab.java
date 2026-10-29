package io.javahub.level7.distributedlockingandcachepatterns;

/**
 * Level 7: Distributed Locking and Cache Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/distributed-locking-and-cache-patterns
 */
public class DistributedlockingandcachepatternsLab {

    private final String topicTitle;
    private final int level;

    public DistributedlockingandcachepatternsLab() {
        this.topicTitle = "Distributed Locking and Cache Patterns";
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
