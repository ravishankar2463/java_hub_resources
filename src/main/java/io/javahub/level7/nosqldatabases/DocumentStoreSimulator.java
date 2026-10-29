package io.javahub.level7.nosqldatabases;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Emulates a NoSQL document database with optimistic concurrency control (version tags).
 */
public class DocumentStoreSimulator {

    public record Document(String id, String jsonPayload, long version) {}

    private final ConcurrentHashMap<String, Document> store = new ConcurrentHashMap<>();

    public void insert(String id, String json) {
        store.put(id, new Document(id, json, 1L));
    }

    public boolean update(String id, String newJson, long expectedVersion) {
        Document current = store.get(id);
        if (current == null || current.version() != expectedVersion) {
            return false; // Optimistic locking collision
        }
        store.put(id, new Document(id, newJson, expectedVersion + 1));
        return true;
    }

    public Document find(String id) { return store.get(id); }
}
