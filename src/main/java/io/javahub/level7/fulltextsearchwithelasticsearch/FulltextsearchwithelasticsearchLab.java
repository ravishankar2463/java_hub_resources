package io.javahub.level7.fulltextsearchwithelasticsearch;

/**
 * Level 7: Full-Text Search with Elasticsearch
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/full-text-search-with-elasticsearch
 */
public class FulltextsearchwithelasticsearchLab {

    private final String topicTitle;
    private final int level;

    public FulltextsearchwithelasticsearchLab() {
        this.topicTitle = "Full-Text Search with Elasticsearch";
        this.level = 7;
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
