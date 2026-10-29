package io.javahub.level7.distributedcachingwithredis;

/**
 * Level 7: Distributed Caching with Redis
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/distributed-caching-with-redis
 */
public class DistributedcachingwithredisLab {

    private final String topicTitle;
    private final int level;

    public DistributedcachingwithredisLab() {
        this.topicTitle = "Distributed Caching with Redis";
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
