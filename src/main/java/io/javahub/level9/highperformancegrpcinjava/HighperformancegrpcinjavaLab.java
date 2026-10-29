package io.javahub.level9.highperformancegrpcinjava;

/**
 * Level 9: High-Performance gRPC in Java
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/high-performance-grpc-in-java
 */
public class HighperformancegrpcinjavaLab {

    private final String topicTitle;
    private final int level;

    public HighperformancegrpcinjavaLab() {
        this.topicTitle = "High-Performance gRPC in Java";
        this.level = 9;
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
