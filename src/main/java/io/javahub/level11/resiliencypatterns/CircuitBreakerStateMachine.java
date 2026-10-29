package io.javahub.level11.resiliencypatterns;

/**
 * Emulates a finite state machine Circuit Breaker protecting services against cascading failures.
 */
public class CircuitBreakerStateMachine {

    public enum State { CLOSED, OPEN, HALF_OPEN }

    private State state = State.CLOSED;
    private int failureCount = 0;
    private final int failureThreshold;

    public CircuitBreakerStateMachine(int failureThreshold) {
        this.failureThreshold = failureThreshold;
    }

    public synchronized boolean allowRequest() {
        return state != State.OPEN;
    }

    public synchronized void recordSuccess() {
        failureCount = 0;
        state = State.CLOSED;
    }

    public synchronized void recordFailure() {
        failureCount++;
        if (failureCount >= failureThreshold) {
            state = State.OPEN; // Trip circuit breaker to OPEN
        }
    }

    public State getState() { return state; }
}
