package io.javahub.level11.microserviceprinciples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BoundedContextServiceBoundaryTest {

    @Test
    @DisplayName("Bounded contexts should maintain independent models without coupling schemas")
    void testBoundedContexts() {
        var boundary = new BoundedContextServiceBoundary();
        var cust = new BoundedContextServiceBoundary.CustomerContextCustomer("C101", "dev@javahub.io");

        String event = boundary.generateCrossContextEvent(cust);
        assertThat(event).contains("CUSTOMER_REGISTERED: C101");
    }
}
