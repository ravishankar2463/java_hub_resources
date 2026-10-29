package io.javahub.level0.introtooops;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BankAccountTest {

    @Test
    @DisplayName("Should successfully deposit and withdraw maintaining balance invariants")
    void testDepositAndWithdraw() {
        BankAccount account = new BankAccount("ACC-9001", new BigDecimal("100.00"));
        account.deposit(new BigDecimal("50.00"));
        account.withdraw(new BigDecimal("30.00"));

        assertThat(account.getBalance()).isEqualByComparingTo("120.00");
    }

    @Test
    @DisplayName("Should reject withdrawal exceeding available balance")
    void testOverdraftProtection() {
        BankAccount account = new BankAccount("ACC-9002", new BigDecimal("50.00"));

        assertThatThrownBy(() -> account.withdraw(new BigDecimal("100.00")))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Insufficient funds");
    }
}
