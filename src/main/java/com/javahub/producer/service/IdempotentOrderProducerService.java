package com.javahub.producer.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class IdempotentOrderProducerService {
    private static final Logger log = LoggerFactory.getLogger(IdempotentOrderProducerService.class);
    public record DispatchResult(String orderId, int partition, long offset, boolean duplicateFiltered) {}
    private final Set<String> processedMessageIds = ConcurrentHashMap.newKeySet();
    public synchronized DispatchResult dispatchOrder(String orderId, String payload, int sequenceNum) {
        String deduplicationKey = orderId + "#" + sequenceNum;
        if (processedMessageIds.contains(deduplicationKey)) {
            log.warn(">> [IDEMPOTENCE TRIGGERED] Duplicate sequence {} discarded for order {}", sequenceNum, orderId);
            return new DispatchResult(orderId, -1, -1, true);
        }
        processedMessageIds.add(deduplicationKey);
        log.info(">> [BROKER_ACK] Written message {} to partition 0 at offset {}", deduplicationKey, processedMessageIds.size());
        return new DispatchResult(orderId, 0, processedMessageIds.size(), false);
    }
}
