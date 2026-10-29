package io.javahub.level8.automatedtesting;

/**
 * Level 8: Automated Testing
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/automated-testing
 */
public class AutomatedtestingLab {

    private final String topicTitle;
    private final int level;

    public AutomatedtestingLab() {
        this.topicTitle = "Automated Testing";
        this.level = 8;
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
