package io.javahub.level10.kafkaproducersanddeliveryguarantees;

import java.util.HashMap;
import java.util.Map;

/**
 * Emulates Kafka idempotent producer sequence number deduplication at the broker partition.
 */
public class IdempotentProducerSimulator {

    public record ProducerRecord(String producerId, long sequenceNumber, String value) {}

    private final Map<String, Long> lastSeenSequence = new HashMap<>();

    public boolean handleRecord(ProducerRecord record) {
        Long lastSeq = lastSeenSequence.get(record.producerId());
        if (lastSeq != null && record.sequenceNumber() <= lastSeq) {
            // Duplicate detected: ACK successfully but drop message to prevent duplicates
            return false;
        }

        lastSeenSequence.put(record.producerId(), record.sequenceNumber());
        return true; // Successfully written to partition log
    }
}
