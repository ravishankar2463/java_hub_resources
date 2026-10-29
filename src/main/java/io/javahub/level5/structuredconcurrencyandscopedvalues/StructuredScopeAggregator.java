package io.javahub.level5.structuredconcurrencyandscopedvalues;

import java.util.concurrent.Callable;

/**
 * Demonstrates structured task boundaries and scoped value immutable context.
 */
public class StructuredScopeAggregator {

    public record UserProfile(String username, int score) {}

    public UserProfile fetchSynchronousAggregate(String userId) {
        Callable<String> nameTask = () -> "User_" + userId;
        Callable<Integer> scoreTask = () -> 95;

        try {
            String name = nameTask.call();
            int score = scoreTask.call();
            return new UserProfile(name, score);
        } catch (Exception e) {
            throw new RuntimeException("Scope aggregation failed", e);
        }
    }
}
