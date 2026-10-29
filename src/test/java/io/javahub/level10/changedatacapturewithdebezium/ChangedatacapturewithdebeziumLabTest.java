package io.javahub.level10.changedatacapturewithdebezium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Change Data Capture with Debezium.
 */
public class ChangedatacapturewithdebeziumLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ChangedatacapturewithdebeziumLab lab = new ChangedatacapturewithdebeziumLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Change Data Capture with Debezium");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
