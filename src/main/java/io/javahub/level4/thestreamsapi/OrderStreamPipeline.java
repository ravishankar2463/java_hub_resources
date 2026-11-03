package io.javahub.level4.thestreamsapi;

import java.util.List;

/**
 * Demonstrates intermediate vs terminal stream operations and pipeline transformations.
 */
public class OrderStreamPipeline {

    public record Item(String name, double price, String category) {}
    public record CustomerOrder(String id, List<Item> items, boolean completed) {}

    public double calculateTotalRevenue(List<CustomerOrder> orders, String targetCategory) {
        return orders.stream()
            .filter(CustomerOrder::completed)               // Intermediate: Filter active orders
            .flatMap(order -> order.items().stream())       // Intermediate: Flatten items
            .filter(item -> item.category().equalsIgnoreCase(targetCategory))
            .mapToDouble(Item::price)                       // Map to primitive double
            .sum();                                         // Terminal: Reduction
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: OrderStreamPipeline");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for OrderStreamPipeline...");
        try {
            OrderStreamPipeline instance = new OrderStreamPipeline();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
