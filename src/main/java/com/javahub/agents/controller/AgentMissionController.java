package com.javahub.agents.controller;
import com.javahub.agents.service.AgentSupervisorLoopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/agents")
public class AgentMissionController {
    private final AgentSupervisorLoopService supervisor;
    public AgentMissionController(AgentSupervisorLoopService supervisor) { this.supervisor = supervisor; }
    @PostMapping("/missions")
    public ResponseEntity<AgentSupervisorLoopService.Mission> launchMission(
            @RequestParam String id, @RequestParam String task) {
        return ResponseEntity.ok(supervisor.runMission(id, task));
    }
}
