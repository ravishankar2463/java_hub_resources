package com.javahub.level14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

class FunctionCallingToolRegistryTest {

    @Test
    @DisplayName("Verify dynamic LLM tool registration and function dispatch")
    void testToolCalling() {
        FunctionCallingToolRegistry registry = new FunctionCallingToolRegistry();

        registry.registerTool(
                "getOrderStatus",
                "Retrieves current shipment status for an order ID",
                "{"orderId": "string"}",
                args -> "Order " + args.get("orderId") + " is SHIPPED via FedEx"
        );

        var result = registry.execute("getOrderStatus", Map.of("orderId", "ORD-9912"));

        assertThat(result.success()).isTrue();
        assertThat(result.output()).isEqualTo("Order ORD-9912 is SHIPPED via FedEx");

        var missing = registry.execute("cancelOrder", Map.of("orderId", "123"));
        assertThat(missing.success()).isFalse();
        assertThat(missing.output()).contains("Unknown tool");
    }
}
