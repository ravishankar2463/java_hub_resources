package io.javahub.level4.advancedstreamscollectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 4: Advanced Streams & Collectors.
 */
public class AdvancedstreamscollectorsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AdvancedstreamscollectorsLab lab = new AdvancedstreamscollectorsLab();

        assertThat(lab.getLevel()).isEqualTo(4);
        assertThat(lab.getTopicTitle()).isEqualTo("Advanced Streams & Collectors");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
