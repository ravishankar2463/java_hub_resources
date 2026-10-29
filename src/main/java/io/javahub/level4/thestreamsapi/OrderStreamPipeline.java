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
}
