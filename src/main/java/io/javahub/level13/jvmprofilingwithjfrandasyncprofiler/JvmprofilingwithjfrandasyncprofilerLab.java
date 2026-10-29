package io.javahub.level13.jvmprofilingwithjfrandasyncprofiler;

/**
 * Level 13: JVM Profiling with JFR and Async-Profiler
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-13/jvm-profiling-with-jfr-and-async-profiler
 */
public class JvmprofilingwithjfrandasyncprofilerLab {

    private final String topicTitle;
    private final int level;

    public JvmprofilingwithjfrandasyncprofilerLab() {
        this.topicTitle = "JVM Profiling with JFR and Async-Profiler";
        this.level = 13;
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
