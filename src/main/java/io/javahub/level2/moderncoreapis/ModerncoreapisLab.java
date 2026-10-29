package io.javahub.level2.moderncoreapis;

/**
 * Level 2: Modern Core APIs
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/modern-core-apis
 */
public class ModerncoreapisLab {

    private final String topicTitle;
    private final int level;

    public ModerncoreapisLab() {
        this.topicTitle = "Modern Core APIs";
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
