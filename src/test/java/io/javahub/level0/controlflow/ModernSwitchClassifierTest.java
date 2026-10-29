package io.javahub.level0.controlflow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ModernSwitchClassifierTest {

    private final ModernSwitchClassifier classifier = new ModernSwitchClassifier();

    @Test
    @DisplayName("Should evaluate guarded pattern matching for high limit credit card")
    void testGuardedSwitch() {
        var card = new ModernSwitchClassifier.CreditCard("4111-2222-3333-4444", 75000);
        String label = classifier.classifyPayment(card);
        assertThat(label).contains("VIP Credit Card");
    }

    @Test
    @DisplayName("Should match Bitcoin crypto record pattern")
    void testCryptoMatch() {
        var crypto = new ModernSwitchClassifier.Crypto("0x123abc", "BTC");
        String label = classifier.classifyPayment(crypto);
        assertThat(label).isEqualTo("Bitcoin On-Chain Payment");
    }

    @Test
    @DisplayName("Should compute cumulative risk scores correctly")
    void testRiskScore() {
        int risk = classifier.calculateRiskScore(10, true, true);
        assertThat(risk).isEqualTo(90);
    }
}
