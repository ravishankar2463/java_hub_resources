package io.javahub.level11.distributedtransactions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Distributed Transactions.
 */
public class DistributedtransactionsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DistributedtransactionsLab lab = new DistributedtransactionsLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Distributed Transactions");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
