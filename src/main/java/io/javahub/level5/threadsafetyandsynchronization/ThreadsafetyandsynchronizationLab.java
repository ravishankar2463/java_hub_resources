package io.javahub.level5.threadsafetyandsynchronization;

/**
 * Level 5: Thread Safety and Synchronization
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-5/thread-safety-and-synchronization
 */
public class ThreadsafetyandsynchronizationLab {

    private final String topicTitle;
    private final int level;

    public ThreadsafetyandsynchronizationLab() {
        this.topicTitle = "Thread Safety and Synchronization";
        this.level = 5;
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
