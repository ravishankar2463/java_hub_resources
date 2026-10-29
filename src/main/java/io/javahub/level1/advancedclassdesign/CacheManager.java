package io.javahub.level1.advancedclassdesign;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Demonstrates static nested classes vs inner classes to eliminate synthetic memory leak hazards.
 */
public class CacheManager {

    // Static nested class has NO hidden pointer to the enclosing CacheManager
    public static class CacheEntry<V> {
        private final V value;
        private final long timestamp;

        public CacheEntry(V value) {
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }

        public V getValue() { return value; }
        public long getTimestamp() { return timestamp; }
    }

    private final Map<String, CacheEntry<String>> storage = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        storage.put(key, new CacheEntry<>(value));
    }

    public String get(String key) {
        CacheEntry<String> entry = storage.get(key);
        return entry != null ? entry.getValue() : null;
    }
}
