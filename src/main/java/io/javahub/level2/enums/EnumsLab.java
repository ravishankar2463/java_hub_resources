package io.javahub.level2.enums;

/**
 * Level 2: Enums
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/enums
 */
public class EnumsLab {

    private final String topicTitle;
    private final int level;

    public EnumsLab() {
        this.topicTitle = "Enums";
        this.level = 2;
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
