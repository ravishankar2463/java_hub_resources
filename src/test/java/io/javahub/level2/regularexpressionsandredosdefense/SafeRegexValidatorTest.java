package io.javahub.level2.regularexpressionsandredosdefense;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SafeRegexValidatorTest {

    private final SafeRegexValidator validator = new SafeRegexValidator();

    @Test
    @DisplayName("Should validate correct emails using possessive quantifiers")
    void testValidEmail() {
        assertThat(validator.isValidEmail("dev@javahub.io")).isTrue();
        assertThat(validator.isValidEmail("invalid-email")).isFalse();
    }

    @Test
    @DisplayName("Timeout guard should interrupt catastrophic backtracking patterns")
    void testReDosInterruption() {
        // Vulnerable catastrophic backtracking regex: (a+)+$
        Pattern evilPattern = Pattern.compile("(a+)+$");
        String evilInput = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa!"; // 28 'a's triggers exponential paths

        assertThatThrownBy(() -> validator.safeTimeoutMatch(evilInput, evilPattern, 50))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Regex execution timeout exceeded");
    }
}
