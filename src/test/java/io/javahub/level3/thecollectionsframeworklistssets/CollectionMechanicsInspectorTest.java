package io.javahub.level3.thecollectionsframeworklistssets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionMechanicsInspectorTest {

    private final CollectionMechanicsInspector inspector = new CollectionMechanicsInspector();

    @Test
    @DisplayName("HashSet must maintain correct element count even under deliberate hash collisions")
    void testHashSetCollisions() {
        Set<CollectionMechanicsInspector.CollisionKey> set = inspector.populateCollidingSet(5);
        assertThat(set).hasSize(5);
    }

    @Test
    @DisplayName("Pre-sized ArrayList should populate efficiently")
    void testArrayList() {
        List<Integer> list = inspector.populateArrayListWithCapacity(100);
        assertThat(list).hasSize(100).startsWith(0).endsWith(99);
    }
}
