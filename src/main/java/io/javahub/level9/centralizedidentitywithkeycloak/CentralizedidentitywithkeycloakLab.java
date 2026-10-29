package io.javahub.level9.centralizedidentitywithkeycloak;

/**
 * Level 9: Centralized Identity with Keycloak
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/centralized-identity-with-keycloak
 */
public class CentralizedidentitywithkeycloakLab {

    private final String topicTitle;
    private final int level;

    public CentralizedidentitywithkeycloakLab() {
        this.topicTitle = "Centralized Identity with Keycloak";
        this.level = 9;
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
