package io.javahub.level3.javaplatformmodulesystemjpms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: Java Platform Module System (JPMS).
 */
public class JavaplatformmodulesystemjpmsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        JavaplatformmodulesystemjpmsLab lab = new JavaplatformmodulesystemjpmsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("Java Platform Module System (JPMS)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
