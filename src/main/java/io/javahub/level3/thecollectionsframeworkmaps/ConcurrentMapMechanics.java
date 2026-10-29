package io.javahub.level3.thecollectionsframeworkmaps;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * High-concurrency map mechanics using ConcurrentHashMap computeIfAbsent.
 */
public class ConcurrentMapMechanics {

    private final ConcurrentHashMap<String, AtomicInteger> accessCounters = new ConcurrentHashMap<>();

    public int incrementHit(String endpoint) {
        // computeIfAbsent is atomic per bucket
        return accessCounters.computeIfAbsent(endpoint, k -> new AtomicInteger(0))
                             .incrementAndGet();
    }

    public int getHits(String endpoint) {
        AtomicInteger counter = accessCounters.get(endpoint);
        return counter != null ? counter.get() : 0;
    }
}
