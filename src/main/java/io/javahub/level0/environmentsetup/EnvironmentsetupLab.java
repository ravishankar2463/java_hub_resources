package io.javahub.level0.environmentsetup;

/**
 * Level 0: Environment Setup
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-0/environment-setup
 */
public class EnvironmentsetupLab {

    private final String topicTitle;
    private final int level;

    public EnvironmentsetupLab() {
        this.topicTitle = "Environment Setup";
        this.level = 0;
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
