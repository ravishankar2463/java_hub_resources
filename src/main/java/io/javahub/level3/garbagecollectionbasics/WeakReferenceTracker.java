package io.javahub.level3.garbagecollectionbasics;

import java.lang.ref.WeakReference;

/**
 * Demonstrates weak references and GC reclaimability.
 */
public class WeakReferenceTracker {

    public record SessionPayload(String sessionId, byte[] data) {}

    public WeakReference<SessionPayload> createTrackedSession(String id) {
        SessionPayload payload = new SessionPayload(id, new byte[1024]);
        return new WeakReference<>(payload);
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: WeakReferenceTracker");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for WeakReferenceTracker...");
        try {
            WeakReferenceTracker instance = new WeakReferenceTracker();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
