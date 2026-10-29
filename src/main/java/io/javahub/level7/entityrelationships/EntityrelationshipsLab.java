package io.javahub.level7.entityrelationships;

/**
 * Level 7: Entity Relationships
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-7/entity-relationships
 */
public class EntityrelationshipsLab {

    private final String topicTitle;
    private final int level;

    public EntityrelationshipsLab() {
        this.topicTitle = "Entity Relationships";
        this.level = 7;
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
