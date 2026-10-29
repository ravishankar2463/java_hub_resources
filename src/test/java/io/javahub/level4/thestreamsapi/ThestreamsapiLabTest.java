package io.javahub.level4.thestreamsapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 4: The Streams API.
 */
public class ThestreamsapiLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThestreamsapiLab lab = new ThestreamsapiLab();

        assertThat(lab.getLevel()).isEqualTo(4);
        assertThat(lab.getTopicTitle()).isEqualTo("The Streams API");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
