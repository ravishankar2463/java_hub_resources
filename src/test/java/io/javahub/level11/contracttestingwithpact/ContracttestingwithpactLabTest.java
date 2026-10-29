package io.javahub.level11.contracttestingwithpact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Contract Testing with Pact.
 */
public class ContracttestingwithpactLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ContracttestingwithpactLab lab = new ContracttestingwithpactLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Contract Testing with Pact");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
