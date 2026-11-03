package io.javahub.level13.tuning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.*;

@RestController
@RequestMapping("/api/v1/jvm")
public class JvmMemoryDiagnosticsController {

    @GetMapping("/memory-pools")
    public ResponseEntity<Map<String, Object>> getMemoryPools() {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String, Object>> poolList = new ArrayList<>();

        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            MemoryUsage usage = pool.getUsage();
            poolList.add(Map.of(
                    "name", pool.getName(),
                    "type", pool.getType().toString(),
                    "usedMb", usage.getUsed() / (1024 * 1024),
                    "committedMb", usage.getCommitted() / (1024 * 1024),
                    "maxMb", usage.getMax() > 0 ? usage.getMax() / (1024 * 1024) : -1
            ));
        }

        List<Map<String, Object>> gcList = new ArrayList<>();
        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            gcList.add(Map.of(
                    "name", gc.getName(),
                    "collectionCount", gc.getCollectionCount(),
                    "collectionTimeMs", gc.getCollectionTime()
            ));
        }

        response.put("memoryPools", poolList);
        response.put("garbageCollectors", gcList);
        return ResponseEntity.ok(response);
    }
}
