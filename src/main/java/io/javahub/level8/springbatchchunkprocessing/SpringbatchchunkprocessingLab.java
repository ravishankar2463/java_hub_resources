package io.javahub.level8.springbatchchunkprocessing;

/**
 * Level 8: Spring Batch: Chunk Processing
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/spring-batch-chunk-processing
 */
public class SpringbatchchunkprocessingLab {

    private final String topicTitle;
    private final int level;

    public SpringbatchchunkprocessingLab() {
        this.topicTitle = "Spring Batch: Chunk Processing";
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
