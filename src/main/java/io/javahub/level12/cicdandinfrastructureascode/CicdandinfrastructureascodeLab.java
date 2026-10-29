package io.javahub.level12.cicdandinfrastructureascode;

/**
 * Level 12: CI/CD and Infrastructure as Code
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-12/cicd-and-infrastructure-as-code
 */
public class CicdandinfrastructureascodeLab {

    private final String topicTitle;
    private final int level;

    public CicdandinfrastructureascodeLab() {
        this.topicTitle = "CI/CD and Infrastructure as Code";
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
