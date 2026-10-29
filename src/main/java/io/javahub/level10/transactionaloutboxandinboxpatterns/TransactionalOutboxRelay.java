package io.javahub.level10.transactionaloutboxandinboxpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Transactional Outbox pattern eliminating dual-write distributed failure modes.
 */
public class TransactionalOutboxRelay {

    public record OutboxMessage(String id, String aggregateType, String payload, boolean published) {}

    private final List<OutboxMessage> outboxTable = new ArrayList<>();

    public synchronized void saveOrderWithOutbox(String orderId, String payload) {
        // Single local atomic transaction inserting order AND outbox record
        outboxTable.add(new OutboxMessage(orderId, "ORDER", payload, false));
    }

    public synchronized List<OutboxMessage> pollUnpublished() {
        return outboxTable.stream().filter(m -> !m.published()).toList();
    }

    public synchronized void markPublished(String id) {
        for (int i = 0; i < outboxTable.size(); i++) {
            if (outboxTable.get(i).id().equals(id)) {
                outboxTable.set(i, new OutboxMessage(id, "ORDER", outboxTable.get(i).payload(), true));
            }
        }
    }
}
