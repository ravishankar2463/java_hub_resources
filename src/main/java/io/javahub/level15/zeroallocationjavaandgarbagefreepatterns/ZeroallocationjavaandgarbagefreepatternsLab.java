package io.javahub.level15.zeroallocationjavaandgarbagefreepatterns;

/**
 * Level 15: Zero-Allocation Java and Garbage-Free Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-15/zero-allocation-java-and-garbage-free-patterns
 */
public class ZeroallocationjavaandgarbagefreepatternsLab {

    private final String topicTitle;
    private final int level;

    public ZeroallocationjavaandgarbagefreepatternsLab() {
        this.topicTitle = "Zero-Allocation Java and Garbage-Free Patterns";
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
