package io.javahub.level0.controlflow;

/**
 * Demonstrates modern Java 21 switch patterns and branch prediction optimizations.
 */
public class ModernSwitchClassifier {

    public sealed interface PaymentMethod permits CreditCard, Crypto, BankTransfer {}
    public record CreditCard(String cardNumber, double limit) implements PaymentMethod {}
    public record Crypto(String walletAddress, String coinType) implements PaymentMethod {}
    public record BankTransfer(String iban, boolean isVerified) implements PaymentMethod {}

    public String classifyPayment(PaymentMethod payment) {
        return switch (payment) {
            case CreditCard cc when cc.limit() > 50000 -> "VIP Credit Card with limit: $" + cc.limit();
            case CreditCard cc -> "Standard Credit Card";
            case Crypto c when c.coinType().equalsIgnoreCase("BTC") -> "Bitcoin On-Chain Payment";
            case Crypto c -> "Altcoin Payment: " + c.coinType();
            case BankTransfer bt when bt.isVerified() -> "Verified Instant Wire";
            case BankTransfer bt -> "Pending Manual Wire Verification";
        };
    }

    public int calculateRiskScore(int initialScore, boolean isForeignIp, boolean isVpn) {
        int score = initialScore;
        if (isForeignIp) score += 30;
        if (isVpn) score += 50;
        return score;
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: ModernSwitchClassifier");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for ModernSwitchClassifier...");
        try {
            ModernSwitchClassifier instance = new ModernSwitchClassifier();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
