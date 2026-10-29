package io.javahub.level15.thelmaxdisruptorandlockfreeringbuffers;

/**
 * Level 15: The LMAX Disruptor and Lock-Free Ring Buffers
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-15/the-lmax-disruptor-and-lock-free-ring-buffers
 */
public class ThelmaxdisruptorandlockfreeringbuffersLab {

    private final String topicTitle;
    private final int level;

    public ThelmaxdisruptorandlockfreeringbuffersLab() {
        this.topicTitle = "The LMAX Disruptor and Lock-Free Ring Buffers";
        this.level = 15;
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
