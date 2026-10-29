package io.javahub.level11.apigatewaysandservicediscovery;

/**
 * Level 11: API Gateways and Service Discovery
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-11/api-gateways-and-service-discovery
 */
public class ApigatewaysandservicediscoveryLab {

    private final String topicTitle;
    private final int level;

    public ApigatewaysandservicediscoveryLab() {
        this.topicTitle = "API Gateways and Service Discovery";
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
