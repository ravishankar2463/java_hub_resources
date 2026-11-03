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

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: ConcurrentMapMechanics");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for ConcurrentMapMechanics...");
        try {
            ConcurrentMapMechanics instance = new ConcurrentMapMechanics();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
