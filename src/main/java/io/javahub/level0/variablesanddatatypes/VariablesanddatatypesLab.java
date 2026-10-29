package io.javahub.level0.variablesanddatatypes;

/**
 * Level 0: Variables and Data Types
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-0/variables-and-data-types
 */
public class VariablesanddatatypesLab {

    private final String topicTitle;
    private final int level;

    public VariablesanddatatypesLab() {
        this.topicTitle = "Variables and Data Types";
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
