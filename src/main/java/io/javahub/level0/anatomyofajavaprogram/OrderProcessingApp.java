package io.javahub.level0.anatomyofajavaprogram;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Enterprise order processing application demonstrating the anatomy of a complete Java file.
 */
public class OrderProcessingApp {

    public record OrderLine(String sku, int quantity, BigDecimal unitPrice) {
        public OrderLine {
            Objects.requireNonNull(sku, "SKU cannot be null");
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
            if (unitPrice.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative");
        }

        public BigDecimal total() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    public record Order(String orderId, List<OrderLine> lines) {
        public BigDecimal calculateGrandTotal() {
            return lines.stream()
                .map(OrderLine::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    // Standard JVM Entry Point
    public static void main(String[] args) {
        Order order = new Order("ORD-1001", List.of(
            new OrderLine("BOOK-JAVA-21", 2, new BigDecimal("49.99")),
            new OrderLine("MUG-COFFEE", 1, new BigDecimal("14.50"))
        ));
        System.out.println("Processing Order " + order.orderId() + " -> Total: $" + order.calculateGrandTotal());
    }

    // Overloaded main method for programmatic invocation
    public static BigDecimal main(Order order) {
        return order.calculateGrandTotal();
    }
}
