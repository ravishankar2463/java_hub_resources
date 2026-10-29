package io.javahub.level11.microserviceprinciples;

/**
 * Domain Bounded Context isolation verifying shared-nothing database boundaries.
 */
public class BoundedContextServiceBoundary {

    public record CustomerContextCustomer(String customerId, String email) {}
    public record BillingContextCustomer(String customerId, String billingAddress, String taxId) {}

    public String generateCrossContextEvent(CustomerContextCustomer c) {
        return "CUSTOMER_REGISTERED: " + c.customerId() + ", " + c.email();
    }
}
