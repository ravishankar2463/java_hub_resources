package io.javahub.level5.projectloomvirtualthreads;

/**
 * Level 5: Project Loom (Virtual Threads)
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-5/project-loom-virtual-threads
 */
public class ProjectloomvirtualthreadsLab {

    private final String topicTitle;
    private final int level;

    public ProjectloomvirtualthreadsLab() {
        this.topicTitle = "Project Loom (Virtual Threads)";
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
