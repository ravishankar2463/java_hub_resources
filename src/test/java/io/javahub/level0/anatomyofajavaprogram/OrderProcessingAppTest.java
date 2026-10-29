package io.javahub.level0.anatomyofajavaprogram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderProcessingAppTest {

    @Test
    @DisplayName("Should correctly compute order grand total from order lines")
    void testOrderCalculation() {
        OrderProcessingApp.Order order = new OrderProcessingApp.Order("ORD-42", List.of(
            new OrderProcessingApp.OrderLine("SKU-1", 3, new BigDecimal("10.00")),
            new OrderProcessingApp.OrderLine("SKU-2", 1, new BigDecimal("5.50"))
        ));

        BigDecimal total = OrderProcessingApp.main(order);
        assertThat(total).isEqualByComparingTo("35.50");
    }

    @Test
    @DisplayName("Should reject invalid order line parameters")
    void testValidationInvariants() {
        assertThatThrownBy(() -> new OrderProcessingApp.OrderLine("SKU-1", -1, BigDecimal.TEN))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Quantity must be positive");
    }
}
