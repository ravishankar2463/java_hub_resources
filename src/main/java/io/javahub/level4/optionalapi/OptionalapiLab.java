package io.javahub.level4.optionalapi;

/**
 * Level 4: Optional API
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-4/optional-api
 */
public class OptionalapiLab {

    private final String topicTitle;
    private final int level;

    public OptionalapiLab() {
        this.topicTitle = "Optional API";
        this.level = 4;
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
