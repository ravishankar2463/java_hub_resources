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
}
