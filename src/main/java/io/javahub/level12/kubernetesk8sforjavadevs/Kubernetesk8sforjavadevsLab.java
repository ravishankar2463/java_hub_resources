package io.javahub.level12.kubernetesk8sforjavadevs;

/**
 * Level 12: Kubernetes (K8s) for Java Devs
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-12/kubernetes-k8s-for-java-devs
 */
public class Kubernetesk8sforjavadevsLab {

    private final String topicTitle;
    private final int level;

    public Kubernetesk8sforjavadevsLab() {
        this.topicTitle = "Kubernetes (K8s) for Java Devs";
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
