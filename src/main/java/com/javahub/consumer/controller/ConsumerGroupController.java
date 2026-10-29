package com.javahub.consumer.controller;
import com.javahub.consumer.service.ConsumerGroupCoordinator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/kafka/group")
public class ConsumerGroupController {
    private final ConsumerGroupCoordinator coordinator;
    public ConsumerGroupController(ConsumerGroupCoordinator coordinator) { this.coordinator = coordinator; }
    @PostMapping("/join")
    public ResponseEntity<List<ConsumerGroupCoordinator.MemberAssignment>> join(@RequestParam String memberId) {
        return ResponseEntity.ok(coordinator.registerMember(memberId));
    }
    @PostMapping("/leave")
    public ResponseEntity<List<ConsumerGroupCoordinator.MemberAssignment>> leave(@RequestParam String memberId) {
        return ResponseEntity.ok(coordinator.removeMember(memberId));
    }
    @GetMapping("/assignments")
    public ResponseEntity<List<ConsumerGroupCoordinator.MemberAssignment>> getAssignments() {
        return ResponseEntity.ok(coordinator.computeAssignments());
    }
}
