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
}
