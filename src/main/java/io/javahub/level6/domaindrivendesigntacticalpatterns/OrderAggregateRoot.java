package io.javahub.level6.domaindrivendesigntacticalpatterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DDD Tactical Aggregate Root encapsulating child entities and publishing Domain Events.
 */
public class OrderAggregateRoot {

    public record DomainEvent(String eventType, String orderId) {}

    private final String orderId;
    private double balanceDue;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public OrderAggregateRoot(String orderId, double initialAmount) {
        if (initialAmount <= 0) throw new IllegalArgumentException("Order amount must be positive");
        this.orderId = orderId;
        this.balanceDue = initialAmount;
        domainEvents.add(new DomainEvent("ORDER_CREATED", orderId));
    }

    public void applyPayment(double paymentAmount) {
        if (paymentAmount <= 0) throw new IllegalArgumentException("Payment must be positive");
        if (paymentAmount > balanceDue) throw new IllegalStateException("Payment exceeds remaining balance");

        this.balanceDue -= paymentAmount;
        if (this.balanceDue == 0.0) {
            domainEvents.add(new DomainEvent("ORDER_PAID_IN_FULL", orderId));
        }
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }

    public double getBalanceDue() { return balanceDue; }
    public String getOrderId() { return orderId; }
}
