package io.javahub.level2.ioandnio2;

/**
 * Level 2: I/O and NIO.2
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/io-and-nio2
 */
public class Ioandnio2Lab {

    private final String topicTitle;
    private final int level;

    public Ioandnio2Lab() {
        this.topicTitle = "I/O and NIO.2";
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
