package io.javahub.level6.architecturalpatterns;

import java.util.Optional;

/**
 * Hexagonal / Clean Architecture domain core decoupled from external storage or messaging.
 */
public class HexagonalOrderCore {

    public record Order(String id, double amount) {}

    // Outbound Port (SPI) implemented by infrastructure database adapters
    public interface OrderRepositoryPort {
        void save(Order order);
        Optional<Order> findById(String id);
    }

    // Inbound Port (API) consumed by controllers or message listeners
    public interface OrderUseCase {
        Order createOrder(String id, double amount);
    }

    // Pure Domain Service without framework dependencies
    public static class OrderService implements OrderUseCase {
        private final OrderRepositoryPort repositoryPort;

        public OrderService(OrderRepositoryPort repositoryPort) {
            this.repositoryPort = repositoryPort;
        }

        @Override
        public Order createOrder(String id, double amount) {
            Order order = new Order(id, amount);
            repositoryPort.save(order);
            return order;
        }
    }
}
