package io.javahub.level2.thestringconstantpoolstringbuilders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: The String Constant Pool & StringBuilders.
 */
public class ThestringconstantpoolstringbuildersLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThestringconstantpoolstringbuildersLab lab = new ThestringconstantpoolstringbuildersLab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("The String Constant Pool & StringBuilders");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
