package com.javahub.testing.unit;

import com.javahub.testing.service.TaxCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaxCalculatorServiceUnitTests {

    private final TaxCalculatorService service = new TaxCalculatorService();

    @Test
    @DisplayName("Unit test: calculate 20% VAT on 100.00")
    void testStandardVat() {
        double vat = service.calculateVat(100.0, 0.20);
        assertThat(vat).isEqualTo(20.00);
    }

    @Test
    @DisplayName("Unit test: negative amount throws IllegalArgumentException")
    void testNegativeAmount() {
        assertThatThrownBy(() -> service.calculateVat(-50.0, 0.20))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
