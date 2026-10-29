package io.javahub.level2.exceptionhandling;

/**
 * Level 2: Exception Handling
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/exception-handling
 */
public class ExceptionhandlingLab {

    private final String topicTitle;
    private final int level;

    public ExceptionhandlingLab() {
        this.topicTitle = "Exception Handling";
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
