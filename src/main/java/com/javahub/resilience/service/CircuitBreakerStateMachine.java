package com.javahub.resilience.service;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;
@Service
public class CircuitBreakerStateMachine {
    public enum State { CLOSED, OPEN, HALF_OPEN }
    private State state = State.CLOSED;
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final int failureThreshold = 3;
    public synchronized String execute(boolean simulateFailure) {
        if (state == State.OPEN) {
            return "FALLBACK_RESPONSE: Fast-failed due to OPEN circuit breaker";
        }
        if (simulateFailure) {
            int fails = failureCount.incrementAndGet();
            if (fails >= failureThreshold) {
                state = State.OPEN;
            }
            return "CALL_FAILED: Failure recorded (current: " + fails + ")";
        }
        failureCount.set(0);
        state = State.CLOSED;
        return "SUCCESS: Remote service executed normally";
    }
    public State getState() { return state; }
    public synchronized void reset() { state = State.CLOSED; failureCount.set(0); }
}
