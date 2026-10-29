package io.javahub.level10.transactionaloutboxandinboxpatterns;

/**
 * Level 10: Transactional Outbox and Inbox Patterns
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/transactional-outbox-and-inbox-patterns
 */
public class TransactionaloutboxandinboxpatternsLab {

    private final String topicTitle;
    private final int level;

    public TransactionaloutboxandinboxpatternsLab() {
        this.topicTitle = "Transactional Outbox and Inbox Patterns";
        this.level = 10;
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
