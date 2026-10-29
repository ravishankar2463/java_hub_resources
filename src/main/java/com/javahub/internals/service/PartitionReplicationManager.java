package com.javahub.internals.service;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PartitionReplicationManager {
    public record PartitionState(int partitionId, int leaderBrokerId, List<Integer> isrBrokers, long highWatermark, long logEndOffset) {}
    private final Map<Integer, PartitionState> stateMap = new HashMap<>();
    public PartitionReplicationManager() {
        stateMap.put(0, new PartitionState(0, 101, List.of(101, 102, 103), 4200L, 4200L));
        stateMap.put(1, new PartitionState(1, 102, List.of(102, 103), 3900L, 3905L));
    }
    public PartitionState getPartition(int id) { return stateMap.getOrDefault(id, stateMap.get(0)); }
    public List<PartitionState> getAllPartitions() { return new ArrayList<>(stateMap.values()); }
}
