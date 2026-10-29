package io.javahub.level10.apachekafkainternals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Apache Kafka Internals.
 */
public class ApachekafkainternalsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ApachekafkainternalsLab lab = new ApachekafkainternalsLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Apache Kafka Internals");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
