package io.javahub.level8.springeventscachingscheduling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionalEventBroadcasterTest {

    @Test
    @DisplayName("Transactional event listener should fire on commit and suppress on rollback")
    void testTransactionalEvents() {
        var broadcaster = new TransactionalEventBroadcaster();

        broadcaster.handleTransaction(new TransactionalEventBroadcaster.OrderPlacedEvent("ORD-1"), true);
        broadcaster.handleTransaction(new TransactionalEventBroadcaster.OrderPlacedEvent("ORD-FAIL"), false);

        assertThat(broadcaster.getAuditLogs()).containsExactly("EMITTED_AFTER_COMMIT: ORD-1");
    }
}
