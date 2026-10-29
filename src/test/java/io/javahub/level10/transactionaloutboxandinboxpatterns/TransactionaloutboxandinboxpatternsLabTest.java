package io.javahub.level10.transactionaloutboxandinboxpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Transactional Outbox and Inbox Patterns.
 */
public class TransactionaloutboxandinboxpatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        TransactionaloutboxandinboxpatternsLab lab = new TransactionaloutboxandinboxpatternsLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Transactional Outbox and Inbox Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
