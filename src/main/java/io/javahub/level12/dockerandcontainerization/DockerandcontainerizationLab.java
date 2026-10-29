package io.javahub.level12.dockerandcontainerization;

/**
 * Level 12: Docker and Containerization
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-12/docker-and-containerization
 */
public class DockerandcontainerizationLab {

    private final String topicTitle;
    private final int level;

    public DockerandcontainerizationLab() {
        this.topicTitle = "Docker and Containerization";
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
