package io.javahub.level8.buildingrestfulapis;

/**
 * Level 8: Building RESTful APIs
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/building-restful-apis
 */
public class BuildingrestfulapisLab {

    private final String topicTitle;
    private final int level;

    public BuildingrestfulapisLab() {
        this.topicTitle = "Building RESTful APIs";
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
