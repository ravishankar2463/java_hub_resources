package io.javahub.level0.controlflow;

/**
 * Level 0: Control Flow
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-0/control-flow
 */
public class ControlflowLab {

    private final String topicTitle;
    private final int level;

    public ControlflowLab() {
        this.topicTitle = "Control Flow";
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
