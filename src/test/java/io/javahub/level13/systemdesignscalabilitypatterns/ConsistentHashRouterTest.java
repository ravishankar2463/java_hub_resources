package io.javahub.level13.systemdesignscalabilitypatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsistentHashRouterTest {

    @Test
    @DisplayName("Consistent hash ring should route keys deterministically to available nodes")
    void testConsistentHashing() {
        var router = new ConsistentHashRouter(10);
        router.addServer("node_1");
        router.addServer("node_2");

        String nodeA = router.routeKey("user_order_1001");
        String nodeB = router.routeKey("user_order_1001"); // Repeat lookup

        assertThat(nodeA).isEqualTo(nodeB);
        assertThat(nodeA).isIn("node_1", "node_2");
    }
}
