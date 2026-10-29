package io.javahub.level10.transactionaloutboxandinboxpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionalOutboxRelayTest {

    @Test
    @DisplayName("Outbox messages should be saved locally and marked published after relay")
    void testTransactionalOutbox() {
        var relay = new TransactionalOutboxRelay();
        relay.saveOrderWithOutbox("ORD-1", "{\"total\": 100}");

        var unpublished = relay.pollUnpublished();
        assertThat(unpublished).hasSize(1);

        relay.markPublished("ORD-1");
        assertThat(relay.pollUnpublished()).isEmpty();
    }
}
