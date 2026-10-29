package io.javahub.level11.longrunningworkflowswithtemporal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TemporalWorkflowSimulatorTest {

    @Test
    @DisplayName("Workflow should produce deterministic sequence of durable execution events")
    void testWorkflowExecutionHistory() {
        var workflow = new TemporalWorkflowSimulator();
        workflow.executeWorkflow("WF-1001");

        assertThat(workflow.getEventHistory()).containsSubsequence(
            "WorkflowStarted: WF-1001",
            "ActivityStarted: ChargeCreditCard",
            "ActivityCompleted: ChargeCreditCard",
            "ActivityStarted: SendConfirmationEmail",
            "ActivityCompleted: SendConfirmationEmail",
            "WorkflowCompleted: WF-1001"
        );
    }
}
