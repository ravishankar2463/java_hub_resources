package io.javahub.level12.googlejibdaemonlesscontainers;

/**
 * Level 12: Google Jib: Daemonless Containers
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-12/google-jib-daemonless-containers
 */
public class GooglejibdaemonlesscontainersLab {

    private final String topicTitle;
    private final int level;

    public GooglejibdaemonlesscontainersLab() {
        this.topicTitle = "Google Jib: Daemonless Containers";
        this.level = 12;
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
