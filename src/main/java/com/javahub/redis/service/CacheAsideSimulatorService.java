package com.javahub.redis.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class CacheAsideSimulatorService {
    private final Map<String, String> simulatedRedis = new ConcurrentHashMap<>();
    public String getWithCacheAside(String key) {
        if (simulatedRedis.containsKey(key)) return "[CACHE_HIT] " + simulatedRedis.get(key);
        String val = "ValueFor_" + key;
        simulatedRedis.put(key, val);
        return "[CACHE_MISS_POPULATED] " + val;
    }
    public void evict(String key) { simulatedRedis.remove(key); }
}
