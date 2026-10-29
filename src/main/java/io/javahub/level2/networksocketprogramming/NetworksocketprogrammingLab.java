package io.javahub.level2.networksocketprogramming;

/**
 * Level 2: Network Socket Programming
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/network-socket-programming
 */
public class NetworksocketprogrammingLab {

    private final String topicTitle;
    private final int level;

    public NetworksocketprogrammingLab() {
        this.topicTitle = "Network Socket Programming";
        this.level = 2;
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
