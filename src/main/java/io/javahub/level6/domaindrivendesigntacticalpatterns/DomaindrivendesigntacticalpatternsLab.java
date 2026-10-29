package io.javahub.level6.domaindrivendesigntacticalpatterns;

/**
 * Level 6: Domain-Driven Design Tactical Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-6/domain-driven-design-tactical-patterns
 */
public class DomaindrivendesigntacticalpatternsLab {

    private final String topicTitle;
    private final int level;

    public DomaindrivendesigntacticalpatternsLab() {
        this.topicTitle = "Domain-Driven Design Tactical Patterns";
        this.level = 6;
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
