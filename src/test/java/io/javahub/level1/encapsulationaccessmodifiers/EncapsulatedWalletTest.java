package io.javahub.level1.encapsulationaccessmodifiers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EncapsulatedWalletTest {

    @Test
    @DisplayName("Should execute valid payments reducing balance")
    void testWalletPayment() {
        EncapsulatedWallet wallet = new EncapsulatedWallet(new BigDecimal("100.00"), "USD");
        wallet.pay(new BigDecimal("40.00"));

        assertThat(wallet.getBalance()).isEqualByComparingTo("60.00");
    }

    @Test
    @DisplayName("Should throw IllegalStateException on insufficient funds")
    void testOverdraftFailure() {
        EncapsulatedWallet wallet = new EncapsulatedWallet(new BigDecimal("20.00"), "USD");

        assertThatThrownBy(() -> wallet.pay(new BigDecimal("50.00")))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Insufficient funds");
    }
}
