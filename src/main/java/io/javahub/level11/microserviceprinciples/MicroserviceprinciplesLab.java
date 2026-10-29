package io.javahub.level11.microserviceprinciples;

/**
 * Level 11: Microservice Principles
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/microservice-principles
 */
public class MicroserviceprinciplesLab {

    private final String topicTitle;
    private final int level;

    public MicroserviceprinciplesLab() {
        this.topicTitle = "Microservice Principles";
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
