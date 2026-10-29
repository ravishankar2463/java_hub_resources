package io.javahub.level6.architecturalpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class HexagonalOrderCoreTest {

    @Test
    @DisplayName("Domain service should execute through port abstraction without external framework code")
    void testHexagonalPortsAndAdapters() {
        Map<String, HexagonalOrderCore.Order> mockDb = new HashMap<>();

        HexagonalOrderCore.OrderRepositoryPort inMemoryRepo = new HexagonalOrderCore.OrderRepositoryPort() {
            @Override
            public void save(HexagonalOrderCore.Order order) { mockDb.put(order.id(), order); }
            @Override
            public Optional<HexagonalOrderCore.Order> findById(String id) { return Optional.ofNullable(mockDb.get(id)); }
        };

        HexagonalOrderCore.OrderService service = new HexagonalOrderCore.OrderService(inMemoryRepo);
        var created = service.createOrder("ORD-42", 250.0);

        assertThat(created.amount()).isEqualTo(250.0);
        assertThat(mockDb).containsKey("ORD-42");
    }
}
