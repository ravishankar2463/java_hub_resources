package io.javahub.level11.longrunningworkflowswithtemporal;

/**
 * Level 11: Long-Running Workflows with Temporal
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/long-running-workflows-with-temporal
 */
public class LongrunningworkflowswithtemporalLab {

    private final String topicTitle;
    private final int level;

    public LongrunningworkflowswithtemporalLab() {
        this.topicTitle = "Long-Running Workflows with Temporal";
        this.level = 11;
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
