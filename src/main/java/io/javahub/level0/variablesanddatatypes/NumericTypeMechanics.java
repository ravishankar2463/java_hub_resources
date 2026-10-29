package io.javahub.level0.variablesanddatatypes;

import java.math.BigDecimal;

/**
 * Demonstrates the physics of Java data types: Integer caching, float precision, and overflow.
 */
public class NumericTypeMechanics {

    public boolean isCachedInteger(int value) {
        Integer first = Integer.valueOf(value);
        Integer second = Integer.valueOf(value);
        // Direct reference equality checks if both reference the JVM IntegerCache object
        return first == second;
    }

    public boolean demonstrateDoublePrecisionLoss() {
        double a = 0.1;
        double b = 0.2;
        // In IEEE 754 floating point arithmetic, 0.1 + 0.2 equals 0.30000000000000004
        return (a + b) != 0.3;
    }

    public BigDecimal computeAccurateDecimal(String a, String b) {
        BigDecimal num1 = new BigDecimal(a);
        BigDecimal num2 = new BigDecimal(b);
        return num1.add(num2);
    }

    public int simulateIntegerOverflow(int initial, int increment) {
        return initial + increment;
    }
}
