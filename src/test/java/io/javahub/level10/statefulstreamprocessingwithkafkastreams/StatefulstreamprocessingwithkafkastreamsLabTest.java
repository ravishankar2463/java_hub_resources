package io.javahub.level10.statefulstreamprocessingwithkafkastreams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Stateful Stream Processing with Kafka Streams.
 */
public class StatefulstreamprocessingwithkafkastreamsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        StatefulstreamprocessingwithkafkastreamsLab lab = new StatefulstreamprocessingwithkafkastreamsLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Stateful Stream Processing with Kafka Streams");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
