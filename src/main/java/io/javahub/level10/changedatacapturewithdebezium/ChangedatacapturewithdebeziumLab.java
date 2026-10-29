package io.javahub.level10.changedatacapturewithdebezium;

/**
 * Level 10: Change Data Capture with Debezium
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/change-data-capture-with-debezium
 */
public class ChangedatacapturewithdebeziumLab {

    private final String topicTitle;
    private final int level;

    public ChangedatacapturewithdebeziumLab() {
        this.topicTitle = "Change Data Capture with Debezium";
        this.level = 10;
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
