package io.javahub.level7.entityrelationships;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BidirectionalRelationshipManagerTest {

    @Test
    @DisplayName("Parent addItem helper must synchronize child back-pointer")
    void testBidirectionalSync() {
        var order = new BidirectionalRelationshipManager.Order("ORD-10");
        var item = new BidirectionalRelationshipManager.OrderItem("SKU-99");

        order.addItem(item);

        assertThat(order.getItems()).containsExactly(item);
        assertThat(item.getOrder()).isSameAs(order);
    }
}
