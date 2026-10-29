package io.javahub.level4.thestreamsapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderStreamPipelineTest {

    @Test
    @DisplayName("Should compute category revenue across completed orders via stream flatMap and sum")
    void testStreamPipeline() {
        OrderStreamPipeline pipeline = new OrderStreamPipeline();

        List<OrderStreamPipeline.CustomerOrder> orders = List.of(
            new OrderStreamPipeline.CustomerOrder("O1", List.of(
                new OrderStreamPipeline.Item("Laptop", 1200.0, "TECH"),
                new OrderStreamPipeline.Item("Book", 30.0, "BOOKS")
            ), true),
            new OrderStreamPipeline.CustomerOrder("O2", List.of(
                new OrderStreamPipeline.Item("Monitor", 400.0, "TECH")
            ), true),
            new OrderStreamPipeline.CustomerOrder("O3", List.of(
                new OrderStreamPipeline.Item("Mouse", 50.0, "TECH")
            ), false) // Incomplete order
        );

        double techRevenue = pipeline.calculateTotalRevenue(orders, "TECH");
        assertThat(techRevenue).isEqualTo(1600.0);
    }
}
