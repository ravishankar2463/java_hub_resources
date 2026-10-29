package com.javahub.streaming.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class LogRetentionStreamSimulator {

    public record PartitionOffset(long offset, String key, String payload, long timestamp) {}

    // Simulates immutable append-only commit log partitioned by key
    private final Map<Integer, List<PartitionOffset>> partitionLogs = new ConcurrentHashMap<>();

    public LogRetentionStreamSimulator() {
        partitionLogs.put(0, new CopyOnWriteArrayList<>());
        partitionLogs.put(1, new CopyOnWriteArrayList<>());
    }

    public synchronized PartitionOffset append(String key, String payload) {
        int partition = Math.abs(key.hashCode() % 2);
        List<PartitionOffset> log = partitionLogs.get(partition);
        long offset = log.size();
        PartitionOffset entry = new PartitionOffset(offset, key, payload, System.currentTimeMillis());
        log.add(entry);
        return entry;
    }

    public List<PartitionOffset> readFromOffset(int partition, long startOffset, int limit) {
        List<PartitionOffset> log = partitionLogs.getOrDefault(partition, List.of());
        return log.stream()
                .filter(e -> e.offset() >= startOffset)
                .limit(limit)
                .toList();
    }
}
