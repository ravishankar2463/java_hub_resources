package io.javahub.level6.creationalpatterns;

/**
 * Level 6: Creational Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-6/creational-patterns
 */
public class CreationalpatternsLab {

    private final String topicTitle;
    private final int level;

    public CreationalpatternsLab() {
        this.topicTitle = "Creational Patterns";
        this.level = 6;
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
