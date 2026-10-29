package io.javahub.level13.systemdesignobservability;

/**
 * Level 13: System Design: Observability
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-13/system-design-observability
 */
public class SystemdesignobservabilityLab {

    private final String topicTitle;
    private final int level;

    public SystemdesignobservabilityLab() {
        this.topicTitle = "System Design: Observability";
        this.level = 13;
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
