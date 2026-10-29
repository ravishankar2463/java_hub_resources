package io.javahub.level3.thecollectionsframeworkmaps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: The Collections Framework (Maps).
 */
public class ThecollectionsframeworkmapsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThecollectionsframeworkmapsLab lab = new ThecollectionsframeworkmapsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("The Collections Framework (Maps)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
