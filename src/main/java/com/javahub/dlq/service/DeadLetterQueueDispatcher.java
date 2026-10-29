package com.javahub.dlq.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class DeadLetterQueueDispatcher {
    private static final Logger log = LoggerFactory.getLogger(DeadLetterQueueDispatcher.class);
    public record DeadLetterEntry(String originalTopic, String payload, String failureReason, int attemptCount, long timestamp) {}
    private final List<DeadLetterEntry> dlqEntries = new CopyOnWriteArrayList<>();
    public synchronized void routeToDlq(String originalTopic, String payload, String reason, int attempts) {
        log.error(">> [DLQ ROUTED] Failed after {} attempts from topic {}: {}", attempts, originalTopic, reason);
        dlqEntries.add(new DeadLetterEntry(originalTopic, payload, reason, attempts, System.currentTimeMillis()));
    }
    public List<DeadLetterEntry> getDlqEntries() { return new ArrayList<>(dlqEntries); }
}
