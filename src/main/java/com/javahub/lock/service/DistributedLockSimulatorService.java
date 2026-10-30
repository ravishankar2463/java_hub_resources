package com.javahub.lock.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class DistributedLockSimulatorService {
    private final Map<String, String> locks = new ConcurrentHashMap<>();
    public synchronized boolean acquireLock(String resource, String owner) {
        return locks.putIfAbsent(resource, owner) == null;
    }
    public synchronized boolean releaseLock(String resource, String owner) {
        if (owner.equals(locks.get(resource))) {
            locks.remove(resource);
            return true;
        }
        return false;
    }
}
