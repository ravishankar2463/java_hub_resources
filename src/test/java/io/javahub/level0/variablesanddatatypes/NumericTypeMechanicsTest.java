package io.javahub.level0.variablesanddatatypes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class NumericTypeMechanicsTest {

    private final NumericTypeMechanics mechanics = new NumericTypeMechanics();

    @Test
    @DisplayName("Integer cache should return identical reference between -128 and 127")
    void testIntegerCacheRange() {
        assertThat(mechanics.isCachedInteger(100)).isTrue();
        assertThat(mechanics.isCachedInteger(-128)).isTrue();
        assertThat(mechanics.isCachedInteger(127)).isTrue();
        assertThat(mechanics.isCachedInteger(128)).isFalse();
        assertThat(mechanics.isCachedInteger(1000)).isFalse();
    }

    @Test
    @DisplayName("IEEE 754 floating point arithmetic should lose precision for 0.1 + 0.2")
    void testFloatingPointAnomaly() {
        assertThat(mechanics.demonstrateDoublePrecisionLoss()).isTrue();
    }

    @Test
    @DisplayName("BigDecimal must guarantee exact financial precision")
    void testBigDecimalAccuracy() {
        BigDecimal result = mechanics.computeAccurateDecimal("0.1", "0.2");
        assertThat(result).isEqualByComparingTo("0.3");
    }

    @Test
    @DisplayName("Integer max value overflow should wrap to Integer.MIN_VALUE")
    void testIntegerOverflow() {
        int overflowed = mechanics.simulateIntegerOverflow(Integer.MAX_VALUE, 1);
        assertThat(overflowed).isEqualTo(Integer.MIN_VALUE);
    }
}
