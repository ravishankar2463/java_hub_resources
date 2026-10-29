package io.javahub.level3.jvmarchitectureinternals;

/**
 * Level 3: JVM Architecture Internals
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-3/jvm-architecture-internals
 */
public class JvmarchitectureinternalsLab {

    private final String topicTitle;
    private final int level;

    public JvmarchitectureinternalsLab() {
        this.topicTitle = "JVM Architecture Internals";
        this.level = 3;
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
