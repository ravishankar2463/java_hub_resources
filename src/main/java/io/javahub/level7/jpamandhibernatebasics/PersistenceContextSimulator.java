package io.javahub.level7.jpamandhibernatebasics;

import java.util.HashMap;
import java.util.Map;

/**
 * Emulates JPA Persistence Context L1 Cache and dirty checking.
 */
public class PersistenceContextSimulator {

    public record EntitySnapshot(String id, String data) {}

    private final Map<String, EntitySnapshot> identityMap = new HashMap<>();
    private final Map<String, EntitySnapshot> loadedSnapshots = new HashMap<>();

    public void persist(String id, String data) {
        var snapshot = new EntitySnapshot(id, data);
        identityMap.put(id, snapshot);
        loadedSnapshots.put(id, snapshot);
    }

    public EntitySnapshot find(String id) {
        return identityMap.get(id); // L1 Cache hit
    }

    public boolean isDirty(String id, String currentData) {
        EntitySnapshot loaded = loadedSnapshots.get(id);
        return loaded != null && !loaded.data().equals(currentData);
    }
}
