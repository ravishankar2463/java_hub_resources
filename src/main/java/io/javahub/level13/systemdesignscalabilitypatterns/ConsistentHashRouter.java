package io.javahub.level13.systemdesignscalabilitypatterns;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Consistent Hashing ring with virtual nodes for distributed database partitioning.
 */
public class ConsistentHashRouter {

    private final SortedMap<Integer, String> ring = new TreeMap<>();
    private final int virtualNodes;

    public ConsistentHashRouter(int virtualNodes) {
        this.virtualNodes = virtualNodes;
    }

    public void addServer(String server) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = (server + "#" + i).hashCode();
            ring.put(hash, server);
        }
    }

    public String routeKey(String key) {
        if (ring.isEmpty()) return null;
        int hash = key.hashCode();
        if (!ring.containsKey(hash)) {
            SortedMap<Integer, String> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(hash);
    }
}
