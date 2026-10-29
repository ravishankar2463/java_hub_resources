package io.javahub.level2.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderStatusStateMachineTest {

    @Test
    @DisplayName("Should successfully transition through valid enum states")
    void testValidTransitions() {
        OrderStatusStateMachine fsm = new OrderStatusStateMachine();
        fsm.transition(OrderStatusStateMachine.Status.PAID);
        fsm.transition(OrderStatusStateMachine.Status.SHIPPED);
        fsm.transition(OrderStatusStateMachine.Status.DELIVERED);

        assertThat(fsm.getCurrentStatus()).isEqualTo(OrderStatusStateMachine.Status.DELIVERED);
    }

    @Test
    @DisplayName("Should reject illegal transition directly from CREATED to SHIPPED")
    void testIllegalTransition() {
        OrderStatusStateMachine fsm = new OrderStatusStateMachine();

        assertThatThrownBy(() -> fsm.transition(OrderStatusStateMachine.Status.SHIPPED))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Illegal transition");
    }
}
