package io.javahub.level10.kafkaproducersanddeliveryguarantees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Kafka Producers and Delivery Guarantees.
 */
public class KafkaproducersanddeliveryguaranteesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        KafkaproducersanddeliveryguaranteesLab lab = new KafkaproducersanddeliveryguaranteesLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Kafka Producers and Delivery Guarantees");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
