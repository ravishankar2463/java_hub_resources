package io.javahub.level1.sealedclassesandpatternmatching;

import java.math.BigDecimal;

/**
 * Demonstrates Java 21 sealed hierarchies and record pattern deconstruction without default branches.
 */
public class PaymentEventProcessor {

    public sealed interface PaymentEvent permits Authorized, Captured, Refunded {}

    public record Authorized(String txId, BigDecimal amount, String cardLast4) implements PaymentEvent {}
    public record Captured(String txId, BigDecimal amount, String settlementBatch) implements PaymentEvent {}
    public record Refunded(String txId, BigDecimal amount, String reason) implements PaymentEvent {}

    public String summarize(PaymentEvent event) {
        // Compiler guarantees 100% exhaustive matching without needing a 'default' branch
        return switch (event) {
            case Authorized(var tx, var amt, var card) -> "Authorized $" + amt + " on card ending in " + card;
            case Captured(var tx, var amt, var batch) -> "Captured $" + amt + " in batch " + batch;
            case Refunded(var tx, var amt, var reason) -> "Refunded $" + amt + " due to: " + reason;
        };
    }
}
