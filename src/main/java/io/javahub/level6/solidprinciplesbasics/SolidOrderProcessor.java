package io.javahub.level6.solidprinciplesbasics;

import java.math.BigDecimal;

/**
 * Clean enterprise ordering architecture adhering strictly to SOLID design principles.
 */
public class SolidOrderProcessor {

    // DIP & ISP: Fine-grained notification abstraction
    public interface NotificationService {
        void sendAlert(String message);
    }

    // OCP: Strategy interface open for extension, closed for modification
    public interface TaxStrategy {
        BigDecimal calculateTax(BigDecimal subtotal);
    }

    public static class StandardVatTax implements TaxStrategy {
        @Override
        public BigDecimal calculateTax(BigDecimal subtotal) {
            return subtotal.multiply(new BigDecimal("0.20")); // 20% VAT
        }
    }

    // SRP: Processor solely orchestrates order financial computation
    private final TaxStrategy taxStrategy;
    private final NotificationService notificationService;

    public SolidOrderProcessor(TaxStrategy taxStrategy, NotificationService notificationService) {
        this.taxStrategy = taxStrategy;
        this.notificationService = notificationService;
    }

    public BigDecimal processTotal(BigDecimal subtotal) {
        BigDecimal tax = taxStrategy.calculateTax(subtotal);
        BigDecimal total = subtotal.add(tax);
        notificationService.sendAlert("Processed order total: $" + total);
        return total;
    }
}
