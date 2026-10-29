package io.javahub.level11.resiliencypatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CircuitBreakerStateMachineTest {

    @Test
    @DisplayName("Circuit breaker should trip to OPEN state upon reaching failure threshold")
    void testCircuitBreakerTripping() {
        var cb = new CircuitBreakerStateMachine(2);

        assertThat(cb.allowRequest()).isTrue();

        cb.recordFailure();
        assertThat(cb.getState()).isEqualTo(CircuitBreakerStateMachine.State.CLOSED);

        cb.recordFailure(); // Threshold 2 reached
        assertThat(cb.getState()).isEqualTo(CircuitBreakerStateMachine.State.OPEN);
        assertThat(cb.allowRequest()).isFalse(); // Sheds traffic to allow upstream recovery
    }
}
