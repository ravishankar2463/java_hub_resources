package io.javahub.level7.entityrelationships;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages bidirectional relationship synchronization between Parent and Child entities.
 */
public class BidirectionalRelationshipManager {

    public static class Order {
        private final String id;
        private final List<OrderItem> items = new ArrayList<>();

        public Order(String id) { this.id = id; }

        public void addItem(OrderItem item) {
            items.add(item);
            item.setOrder(this); // Synchronize bidirectional pointer
        }

        public List<OrderItem> getItems() { return List.copyOf(items); }
        public String getId() { return id; }
    }

    public static class OrderItem {
        private final String sku;
        private Order order;

        public OrderItem(String sku) { this.sku = sku; }
        public void setOrder(Order order) { this.order = order; }
        public Order getOrder() { return order; }
        public String getSku() { return sku; }
    }
}
