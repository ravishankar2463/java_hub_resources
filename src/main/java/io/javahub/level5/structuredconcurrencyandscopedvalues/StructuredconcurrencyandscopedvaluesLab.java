package io.javahub.level5.structuredconcurrencyandscopedvalues;

/**
 * Level 5: Structured Concurrency and Scoped Values
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-5/structured-concurrency-and-scoped-values
 */
public class StructuredconcurrencyandscopedvaluesLab {

    private final String topicTitle;
    private final int level;

    public StructuredconcurrencyandscopedvaluesLab() {
        this.topicTitle = "Structured Concurrency and Scoped Values";
        this.level = 5;
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
