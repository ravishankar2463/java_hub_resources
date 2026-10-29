package io.javahub.level3.thecollectionsframeworkmaps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConcurrentMapMechanicsTest {

    @Test
    @DisplayName("ConcurrentHashMap computeIfAbsent must track hits atomically")
    void testComputeIfAbsent() {
        ConcurrentMapMechanics tracker = new ConcurrentMapMechanics();
        tracker.incrementHit("/api/v1/orders");
        tracker.incrementHit("/api/v1/orders");
        tracker.incrementHit("/api/v1/users");

        assertThat(tracker.getHits("/api/v1/orders")).isEqualTo(2);
        assertThat(tracker.getHits("/api/v1/users")).isEqualTo(1);
        assertThat(tracker.getHits("/api/v1/products")).isEqualTo(0);
    }
}
