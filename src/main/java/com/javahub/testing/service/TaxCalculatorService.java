package com.javahub.testing.service;

import org.springframework.stereotype.Service;

@Service
public class TaxCalculatorService {
    public double calculateVat(double amount, double taxRate) {
        if (amount < 0 || taxRate < 0) {
            throw new IllegalArgumentException("Amount and tax rate must be non-negative");
        }
        return Math.round((amount * taxRate) * 100.0) / 100.0;
    }
}
