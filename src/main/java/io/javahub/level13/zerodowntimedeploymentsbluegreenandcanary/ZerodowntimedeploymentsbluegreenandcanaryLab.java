package io.javahub.level13.zerodowntimedeploymentsbluegreenandcanary;

/**
 * Level 13: Zero-Downtime Deployments: Blue-Green and Canary
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-13/zero-downtime-deployments-blue-green-and-canary
 */
public class ZerodowntimedeploymentsbluegreenandcanaryLab {

    private final String topicTitle;
    private final int level;

    public ZerodowntimedeploymentsbluegreenandcanaryLab() {
        this.topicTitle = "Zero-Downtime Deployments: Blue-Green and Canary";
        this.level = 13;
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
