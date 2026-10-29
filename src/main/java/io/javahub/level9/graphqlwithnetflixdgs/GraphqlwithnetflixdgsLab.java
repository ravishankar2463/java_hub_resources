package io.javahub.level9.graphqlwithnetflixdgs;

/**
 * Level 9: GraphQL with Netflix DGS
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/graphql-with-netflix-dgs
 */
public class GraphqlwithnetflixdgsLab {

    private final String topicTitle;
    private final int level;

    public GraphqlwithnetflixdgsLab() {
        this.topicTitle = "GraphQL with Netflix DGS";
        this.level = 9;
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
