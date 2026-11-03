package io.javahub.level1.abstraction;

import java.math.BigDecimal;

/**
 * Demonstrates interface abstraction, default methods, and static factory helpers.
 */
public interface PaymentGateway {

    boolean processPayment(String transactionId, BigDecimal amount);

    default String generateReceipt(String transactionId, BigDecimal amount) {
        return "Receipt for Tx: " + transactionId + ", Amount: $" + amount;
    }

    static PaymentGateway createMockGateway(boolean alwaysSucceed) {
        return (txId, amount) -> alwaysSucceed;
    }

    static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: Abstraction & Interface Contracts");
        System.out.println("================================================================================");
        PaymentGateway gateway = PaymentGateway.createMockGateway(true);
        boolean success = gateway.processPayment("TX-8823", java.math.BigDecimal.valueOf(149.99));
        String receipt = gateway.generateReceipt("TX-8823", java.math.BigDecimal.valueOf(149.99));
        System.out.println("Payment Processed : " + success);
        System.out.println("Receipt Generated : " + receipt);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
