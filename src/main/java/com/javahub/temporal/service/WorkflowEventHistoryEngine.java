package com.javahub.temporal.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class WorkflowEventHistoryEngine {
    public record WorkflowHistory(String workflowId, String state, List<String> eventHistory) {}
    private final Map<String, WorkflowHistory> workflowStore = new ConcurrentHashMap<>();
    public synchronized WorkflowHistory startWorkflow(String workflowId) {
        List<String> events = new ArrayList<>(List.of("WorkflowExecutionStarted", "ActivityScheduled: KYC_VERIFY", "ActivityCompleted: KYC_VERIFY"));
        WorkflowHistory wh = new WorkflowHistory(workflowId, "RUNNING_WAITING_FOR_TIMER", events);
        workflowStore.put(workflowId, wh);
        return wh;
    }
    public WorkflowHistory getWorkflow(String id) { return workflowStore.get(id); }
}
