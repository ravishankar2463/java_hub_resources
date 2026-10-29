package io.javahub.level4.lambdasandfunctionalinterfaces;

/**
 * Level 4: Lambdas and Functional Interfaces
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-4/lambdas-and-functional-interfaces
 */
public class LambdasandfunctionalinterfacesLab {

    private final String topicTitle;
    private final int level;

    public LambdasandfunctionalinterfacesLab() {
        this.topicTitle = "Lambdas and Functional Interfaces";
        this.level = 4;
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
