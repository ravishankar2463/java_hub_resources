package io.javahub.level9.realtimemessagingwebsocketssse;

/**
 * Level 9: Real-Time Messaging: WebSockets & SSE
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-9/real-time-messaging-websockets-sse
 */
public class RealtimemessagingwebsocketssseLab {

    private final String topicTitle;
    private final int level;

    public RealtimemessagingwebsocketssseLab() {
        this.topicTitle = "Real-Time Messaging: WebSockets & SSE";
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
