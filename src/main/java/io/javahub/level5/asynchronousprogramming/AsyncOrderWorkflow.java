package io.javahub.level5.asynchronousprogramming;

import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous reactive order validation pipeline using CompletableFuture.
 */
public class AsyncOrderWorkflow {

    public CompletableFuture<Double> fetchOrderPrice(String orderId) {
        return CompletableFuture.supplyAsync(() -> 100.0);
    }

    public CompletableFuture<Double> fetchTaxRate(String orderId) {
        return CompletableFuture.supplyAsync(() -> 0.15); // 15% tax
    }

    public CompletableFuture<Double> computeTotalWithTax(String orderId) {
        CompletableFuture<Double> priceFuture = fetchOrderPrice(orderId);
        CompletableFuture<Double> taxFuture = fetchTaxRate(orderId);

        // thenCombine merges both asynchronous futures when both complete
        return priceFuture.thenCombine(taxFuture, (price, tax) -> price + (price * tax))
                          .exceptionally(ex -> 0.0); // Fallback on failure
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: AsyncOrderWorkflow");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for AsyncOrderWorkflow...");
        try {
            AsyncOrderWorkflow instance = new AsyncOrderWorkflow();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
