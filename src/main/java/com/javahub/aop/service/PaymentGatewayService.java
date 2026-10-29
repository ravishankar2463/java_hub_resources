package com.javahub.aop.service;

import com.javahub.aop.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentGatewayService {

    @LogExecutionTime("Process Card Payment")
    public String chargeCreditCard(String customerId, double amount) throws InterruptedException {
        // Simulate remote payment gateway processing latency
        Thread.sleep(80);
        return "PAYMENT_CONFIRMED: " + UUID.randomUUID() + " for $" + amount;
    }

    @LogExecutionTime("Refund Payment")
    public String refund(String transactionId) throws InterruptedException {
        Thread.sleep(40);
        return "REFUND_PROCESSED: " + transactionId;
    }
}
