package io.javahub.level6.domaindrivendesigntacticalpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderAggregateRootTest {

    @Test
    @DisplayName("Aggregate Root must preserve transactional invariants and emit domain events")
    void testAggregateInvariants() {
        OrderAggregateRoot order = new OrderAggregateRoot("ORD-77", 100.0);
        order.applyPayment(100.0);

        assertThat(order.getBalanceDue()).isEqualTo(0.0);
        List<OrderAggregateRoot.DomainEvent> events = order.pullDomainEvents();

        assertThat(events).extracting(OrderAggregateRoot.DomainEvent::eventType)
                          .containsExactly("ORDER_CREATED", "ORDER_PAID_IN_FULL");
    }

    @Test
    @DisplayName("Should prevent overpayment violating aggregate invariant")
    void testOverpaymentRejection() {
        OrderAggregateRoot order = new OrderAggregateRoot("ORD-78", 50.0);

        assertThatThrownBy(() -> order.applyPayment(70.0))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Payment exceeds remaining balance");
    }
}
