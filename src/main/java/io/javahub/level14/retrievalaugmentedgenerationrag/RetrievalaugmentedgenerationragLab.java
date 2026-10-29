package io.javahub.level14.retrievalaugmentedgenerationrag;

/**
 * Level 14: Retrieval-Augmented Generation (RAG)
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-14/retrieval-augmented-generation-rag
 */
public class RetrievalaugmentedgenerationragLab {

    private final String topicTitle;
    private final int level;

    public RetrievalaugmentedgenerationragLab() {
        this.topicTitle = "Retrieval-Augmented Generation (RAG)";
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
