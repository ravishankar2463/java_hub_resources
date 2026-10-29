package io.javahub.level11.contracttestingwithpact;

/**
 * Level 11: Contract Testing with Pact
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/contract-testing-with-pact
 */
public class ContracttestingwithpactLab {

    private final String topicTitle;
    private final int level;

    public ContracttestingwithpactLab() {
        this.topicTitle = "Contract Testing with Pact";
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
