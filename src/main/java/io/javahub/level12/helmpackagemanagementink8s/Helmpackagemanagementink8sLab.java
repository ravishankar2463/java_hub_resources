package io.javahub.level12.helmpackagemanagementink8s;

/**
 * Level 12: Helm Package Management in K8s
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-12/helm-package-management-in-k8s
 */
public class Helmpackagemanagementink8sLab {

    private final String topicTitle;
    private final int level;

    public Helmpackagemanagementink8sLab() {
        this.topicTitle = "Helm Package Management in K8s";
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
