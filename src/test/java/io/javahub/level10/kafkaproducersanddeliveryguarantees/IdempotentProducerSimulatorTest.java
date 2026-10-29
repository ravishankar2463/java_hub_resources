package io.javahub.level10.kafkaproducersanddeliveryguarantees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IdempotentProducerSimulatorTest {

    @Test
    @DisplayName("Should accept new sequential records but drop duplicate sequence numbers")
    void testDeduplication() {
        var broker = new IdempotentProducerSimulator();

        assertThat(broker.handleRecord(new IdempotentProducerSimulator.ProducerRecord("PID-1", 0, "order_1"))).isTrue();
        assertThat(broker.handleRecord(new IdempotentProducerSimulator.ProducerRecord("PID-1", 1, "order_2"))).isTrue();

        // Retry of sequence 1 due to transient network timeout
        assertThat(broker.handleRecord(new IdempotentProducerSimulator.ProducerRecord("PID-1", 1, "order_2"))).isFalse();
    }
}
