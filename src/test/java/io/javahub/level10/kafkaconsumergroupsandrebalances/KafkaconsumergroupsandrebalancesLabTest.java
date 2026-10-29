package io.javahub.level10.kafkaconsumergroupsandrebalances;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Kafka Consumer Groups and Rebalances.
 */
public class KafkaconsumergroupsandrebalancesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        KafkaconsumergroupsandrebalancesLab lab = new KafkaconsumergroupsandrebalancesLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Kafka Consumer Groups and Rebalances");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
