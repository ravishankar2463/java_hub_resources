package io.javahub.level5.executorsandthreadpools;

/**
 * Level 5: Executors and Thread Pools
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-5/executors-and-thread-pools
 */
public class ExecutorsandthreadpoolsLab {

    private final String topicTitle;
    private final int level;

    public ExecutorsandthreadpoolsLab() {
        this.topicTitle = "Executors and Thread Pools";
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
