package com.javahub.internals.controller;
import com.javahub.internals.service.PartitionReplicationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/kafka/internals")
public class KafkaInternalsController {
    private final PartitionReplicationManager replicationManager;
    public KafkaInternalsController(PartitionReplicationManager replicationManager) { this.replicationManager = replicationManager; }
    @GetMapping("/partitions")
    public ResponseEntity<List<PartitionReplicationManager.PartitionState>> getPartitions() {
        return ResponseEntity.ok(replicationManager.getAllPartitions());
    }
}
