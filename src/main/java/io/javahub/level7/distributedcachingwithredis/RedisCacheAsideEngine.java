package io.javahub.level7.distributedcachingwithredis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Emulates the Cache-Aside pattern with Redis key-value storage.
 */
public class RedisCacheAsideEngine<K, V> {

    private final Map<K, V> cache = new ConcurrentHashMap<>();

    public V getOrLoad(K key, Function<K, V> databaseLoader) {
        V cached = cache.get(key);
        if (cached != null) {
            return cached; // Cache Hit
        }

        // Cache Miss -> Load from database and populate cache
        V fresh = databaseLoader.apply(key);
        if (fresh != null) {
            cache.put(key, fresh);
        }
        return fresh;
    }

    public void evict(K key) {
        cache.remove(key);
    }
}
