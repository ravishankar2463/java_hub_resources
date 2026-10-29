package io.javahub.level8.aspectorientedprogrammingaop;

import java.util.function.Supplier;

/**
 * Emulates Spring AOP @Around aspect measurement of method execution times.
 */
public class ExecutionTimeAspectSimulator {

    public record ExecutionResult<T>(T result, long durationNanos) {}

    public <T> ExecutionResult<T> profileMethod(Supplier<T> jointPointInvocation) {
        long start = System.nanoTime();
        try {
            T value = jointPointInvocation.get();
            long duration = System.nanoTime() - start;
            return new ExecutionResult<>(value, duration);
        } catch (Exception e) {
            throw new RuntimeException("Aspect execution intercepted exception", e);
        }
    }
}
