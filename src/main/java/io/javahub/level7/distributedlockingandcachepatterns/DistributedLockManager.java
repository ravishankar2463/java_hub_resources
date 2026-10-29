package io.javahub.level7.distributedlockingandcachepatterns;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Emulates distributed locking semantics with token leases.
 */
public class DistributedLockManager {

    private final ConcurrentHashMap<String, String> locks = new ConcurrentHashMap<>();

    public boolean tryAcquire(String lockKey, String ownerToken) {
        // Atomic putIfAbsent guarantees single lock holder
        return locks.putIfAbsent(lockKey, ownerToken) == null;
    }

    public boolean release(String lockKey, String ownerToken) {
        // Only release if the token matches the lock owner
        return locks.remove(lockKey, ownerToken);
    }
}
