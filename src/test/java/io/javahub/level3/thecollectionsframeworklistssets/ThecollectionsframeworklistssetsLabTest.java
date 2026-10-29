package io.javahub.level3.thecollectionsframeworklistssets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: The Collections Framework (Lists & Sets).
 */
public class ThecollectionsframeworklistssetsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThecollectionsframeworklistssetsLab lab = new ThecollectionsframeworklistssetsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("The Collections Framework (Lists & Sets)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
