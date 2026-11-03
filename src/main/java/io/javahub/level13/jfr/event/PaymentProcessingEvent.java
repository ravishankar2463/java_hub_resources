package io.javahub.level13.jfr.event;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.javahub.PaymentProcessing")
@Label("Payment Processing Transaction")
@Category({"JavaHub", "Business Logic"})
@Description("Records the execution latency and outcome of enterprise payment flows")
public class PaymentProcessingEvent extends Event {

    @Label("Transaction ID")
    private String transactionId;

    @Label("Amount")
    private double amount;

    @Label("Gateway Status")
    private String status;

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
