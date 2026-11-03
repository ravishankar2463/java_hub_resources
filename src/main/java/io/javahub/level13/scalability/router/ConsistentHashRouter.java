package io.javahub.level13.scalability.router;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ConsistentHashRouter {

    private final SortedMap<Long, String> ring = new TreeMap<>();
    private final int numberOfReplicas;
    private final Set<String> physicalNodes = new HashSet<>();

    public ConsistentHashRouter() {
        this(100); // 100 virtual nodes per physical host by default
        addNode("storage-node-east-1");
        addNode("storage-node-west-2");
        addNode("storage-node-eu-central-1");
    }

    public ConsistentHashRouter(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }

    public synchronized void addNode(String node) {
        physicalNodes.add(node);
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(node + "-vnode-" + i);
            ring.put(hash, node);
        }
    }

    public synchronized void removeNode(String node) {
        physicalNodes.remove(node);
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(node + "-vnode-" + i);
            ring.remove(hash);
        }
    }

    public synchronized String routeKey(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        long hash = hash(key);
        if (!ring.containsKey(hash)) {
            SortedMap<Long, String> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(hash);
    }

    public synchronized Set<String> getPhysicalNodes() {
        return Collections.unmodifiableSet(physicalNodes);
    }

    public synchronized int getVirtualNodeCount() {
        return ring.size();
    }

    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(key.getBytes(StandardCharsets.UTF_8));
            // 32-bit integer truncation from digest
            return ((long) (digest[3] & 0xFF) << 24)
                    | ((long) (digest[2] & 0xFF) << 16)
                    | ((long) (digest[1] & 0xFF) << 8)
                    | ((long) (digest[0] & 0xFF));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not available", e);
        }
    }
}
