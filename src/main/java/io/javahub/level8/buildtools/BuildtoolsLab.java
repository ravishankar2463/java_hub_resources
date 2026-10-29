package io.javahub.level8.buildtools;

/**
 * Level 8: Build Tools
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/build-tools
 */
public class BuildtoolsLab {

    private final String topicTitle;
    private final int level;

    public BuildtoolsLab() {
        this.topicTitle = "Build Tools";
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
