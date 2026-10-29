package io.javahub.level8.springeventscachingscheduling;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates Spring @TransactionalEventListener firing only on transaction commit.
 */
public class TransactionalEventBroadcaster {

    public record OrderPlacedEvent(String orderId) {}

    private final List<String> auditLogs = new ArrayList<>();

    public void handleTransaction(OrderPlacedEvent event, boolean commit) {
        if (commit) {
            // Fires strictly AFTER_COMMIT
            auditLogs.add("EMITTED_AFTER_COMMIT: " + event.orderId());
        }
        // Rolled back events are safely suppressed
    }

    public List<String> getAuditLogs() { return List.copyOf(auditLogs); }
}
