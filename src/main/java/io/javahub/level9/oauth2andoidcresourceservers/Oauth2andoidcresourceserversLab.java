package io.javahub.level9.oauth2andoidcresourceservers;

/**
 * Level 9: OAuth2 and OIDC Resource Servers
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/oauth2-and-oidc-resource-servers
 */
public class Oauth2andoidcresourceserversLab {

    private final String topicTitle;
    private final int level;

    public Oauth2andoidcresourceserversLab() {
        this.topicTitle = "OAuth2 and OIDC Resource Servers";
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
