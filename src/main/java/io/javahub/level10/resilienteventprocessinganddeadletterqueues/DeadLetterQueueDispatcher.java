package io.javahub.level10.resilienteventprocessinganddeadletterqueues;

import java.util.ArrayList;
import java.util.List;

/**
 * Dead Letter Queue (DLQ) retry and escalation handler.
 */
public class DeadLetterQueueDispatcher {

    private final List<String> mainQueue = new ArrayList<>();
    private final List<String> deadLetterQueue = new ArrayList<>();

    public void processMessage(String message, int attempts, int maxRetries) {
        if (attempts >= maxRetries) {
            deadLetterQueue.add(message); // Route to DLQ after exhausting retries
        } else {
            mainQueue.add(message); // Re-queue with exponential backoff
        }
    }

    public List<String> getDeadLetterQueue() { return List.copyOf(deadLetterQueue); }
    public List<String> getMainQueue() { return List.copyOf(mainQueue); }
}
