package io.javahub.level11.interservicecommunication;

/**
 * Level 11: Inter-Service Communication
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/inter-service-communication
 */
public class InterservicecommunicationLab {

    private final String topicTitle;
    private final int level;

    public InterservicecommunicationLab() {
        this.topicTitle = "Inter-Service Communication";
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
