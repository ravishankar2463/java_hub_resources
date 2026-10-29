package io.javahub.level4.thestreamsapi;

/**
 * Level 4: The Streams API
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-4/the-streams-api
 */
public class ThestreamsapiLab {

    private final String topicTitle;
    private final int level;

    public ThestreamsapiLab() {
        this.topicTitle = "The Streams API";
        this.level = 4;
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
