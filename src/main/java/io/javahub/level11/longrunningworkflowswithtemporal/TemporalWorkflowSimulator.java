package io.javahub.level11.longrunningworkflowswithtemporal;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates Temporal deterministic workflow execution and activity history logging.
 */
public class TemporalWorkflowSimulator {

    private final List<String> eventHistory = new ArrayList<>();

    public void executeWorkflow(String workflowId) {
        recordEvent("WorkflowStarted: " + workflowId);
        executeActivity("ChargeCreditCard");
        executeActivity("SendConfirmationEmail");
        recordEvent("WorkflowCompleted: " + workflowId);
    }

    private void executeActivity(String activityName) {
        recordEvent("ActivityStarted: " + activityName);
        // Emulates activity execution
        recordEvent("ActivityCompleted: " + activityName);
    }

    private void recordEvent(String event) { eventHistory.add(event); }

    public List<String> getEventHistory() { return List.copyOf(eventHistory); }
}
