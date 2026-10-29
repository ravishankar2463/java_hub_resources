package io.javahub.level9.statelessauthenticationjwt;

/**
 * Level 9: Stateless Authentication (JWT)
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/stateless-authentication-jwt
 */
public class StatelessauthenticationjwtLab {

    private final String topicTitle;
    private final int level;

    public StatelessauthenticationjwtLab() {
        this.topicTitle = "Stateless Authentication (JWT)";
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
