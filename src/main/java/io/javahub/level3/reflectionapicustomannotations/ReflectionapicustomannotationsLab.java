package io.javahub.level3.reflectionapicustomannotations;

/**
 * Level 3: Reflection API & Custom Annotations
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-3/reflection-api-custom-annotations
 */
public class ReflectionapicustomannotationsLab {

    private final String topicTitle;
    private final int level;

    public ReflectionapicustomannotationsLab() {
        this.topicTitle = "Reflection API & Custom Annotations";
        this.level = 3;
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
