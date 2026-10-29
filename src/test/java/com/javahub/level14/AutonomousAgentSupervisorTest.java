package com.javahub.level14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class AutonomousAgentSupervisorTest {

    @Test
    @DisplayName("Verify multi-agent workflow choreography and execution trace")
    void testAutonomousMission() {
        AutonomousAgentSupervisor supervisor = new AutonomousAgentSupervisor();
        var report = supervisor.executeMission("TASK-808", "Build Panama Memory Arena Wrapper");

        assertThat(report.success()).isTrue();
        assertThat(report.missionId()).isEqualTo("TASK-808");
        assertThat(report.steps()).hasSize(4);
        assertThat(report.steps().get(0).role()).isEqualTo(AutonomousAgentSupervisor.AgentRole.PLANNER);
        assertThat(report.steps().get(3).role()).isEqualTo(AutonomousAgentSupervisor.AgentRole.REVIEWER);
        assertThat(report.steps().get(3).result()).contains("Approved");
    }
}
