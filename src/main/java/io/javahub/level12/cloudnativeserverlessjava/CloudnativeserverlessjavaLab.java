package io.javahub.level12.cloudnativeserverlessjava;

/**
 * Level 12: Cloud Native & Serverless Java
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-12/cloud-native-serverless-java
 */
public class CloudnativeserverlessjavaLab {

    private final String topicTitle;
    private final int level;

    public CloudnativeserverlessjavaLab() {
        this.topicTitle = "Cloud Native & Serverless Java";
        this.level = 12;
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
