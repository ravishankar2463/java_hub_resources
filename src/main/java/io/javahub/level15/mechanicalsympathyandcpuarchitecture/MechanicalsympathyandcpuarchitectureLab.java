package io.javahub.level15.mechanicalsympathyandcpuarchitecture;

/**
 * Level 15: Mechanical Sympathy and CPU Architecture
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-15/mechanical-sympathy-and-cpu-architecture
 */
public class MechanicalsympathyandcpuarchitectureLab {

    private final String topicTitle;
    private final int level;

    public MechanicalsympathyandcpuarchitectureLab() {
        this.topicTitle = "Mechanical Sympathy and CPU Architecture";
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
