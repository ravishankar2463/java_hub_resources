package io.javahub.level14.vectordatabasesandsemanticsearch;

/**
 * Level 14: Vector Databases and Semantic Search
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-14/vector-databases-and-semantic-search
 */
public class VectordatabasesandsemanticsearchLab {

    private final String topicTitle;
    private final int level;

    public VectordatabasesandsemanticsearchLab() {
        this.topicTitle = "Vector Databases and Semantic Search";
        this.level = 14;
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
