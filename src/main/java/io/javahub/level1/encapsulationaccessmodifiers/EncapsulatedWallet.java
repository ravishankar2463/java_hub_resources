package io.javahub.level1.encapsulationaccessmodifiers;

import java.math.BigDecimal;

/**
 * Encapsulated financial wallet adhering to the Tell-Don't-Ask principle.
 */
public class EncapsulatedWallet {

    private BigDecimal balance;
    private final String currency;

    public EncapsulatedWallet(BigDecimal initialBalance, String currency) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
        this.currency = currency;
    }

    public synchronized void pay(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds to complete payment");
        }
        this.balance = this.balance.subtract(amount);
    }

    public synchronized void topUp(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Top-up amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
