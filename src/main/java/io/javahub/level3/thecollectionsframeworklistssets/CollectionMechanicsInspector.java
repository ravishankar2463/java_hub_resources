package io.javahub.level3.thecollectionsframeworklistssets;

import java.util.*;

/**
 * Demonstrates collection growth, hashing collisions, and performance characteristics.
 */
public class CollectionMechanicsInspector {

    public record CollisionKey(String key, int forcedHashCode) {
        @Override
        public int hashCode() {
            return forcedHashCode; // Force identical bucket placement
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof CollisionKey other)) return false;
            return Objects.equals(this.key, other.key);
        }
    }

    public Set<CollisionKey> populateCollidingSet(int count) {
        Set<CollisionKey> set = new HashSet<>();
        for (int i = 0; i < count; i++) {
            set.add(new CollisionKey("item_" + i, 42)); // All share bucket hash 42
        }
        return set;
    }

    public List<Integer> populateArrayListWithCapacity(int capacity) {
        List<Integer> list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            list.add(i);
        }
        return list;
    }
}
