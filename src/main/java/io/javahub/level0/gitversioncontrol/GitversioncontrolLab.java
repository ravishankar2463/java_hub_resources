package io.javahub.level0.gitversioncontrol;

/**
 * Level 0: Git & Version Control
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-0/git-version-control
 */
public class GitversioncontrolLab {

    private final String topicTitle;
    private final int level;

    public GitversioncontrolLab() {
        this.topicTitle = "Git & Version Control";
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
