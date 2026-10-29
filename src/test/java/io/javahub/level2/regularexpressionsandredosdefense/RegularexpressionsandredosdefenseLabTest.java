package io.javahub.level2.regularexpressionsandredosdefense;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: Regular Expressions and ReDoS Defense.
 */
public class RegularexpressionsandredosdefenseLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        RegularexpressionsandredosdefenseLab lab = new RegularexpressionsandredosdefenseLab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("Regular Expressions and ReDoS Defense");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
