package io.javahub.level10.apachekafkainternals;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates a single Kafka partition log with LEO and High Watermark commit thresholds.
 */
public class KafkaPartitionLog {

    public record Record(long offset, String payload) {}

    private final List<Record> log = new ArrayList<>();
    private long highWatermark = 0L; // Offset up to which records are replicated to all ISRs

    public synchronized long append(String payload) {
        long offset = log.size();
        log.add(new Record(offset, payload));
        return offset; // Log End Offset (LEO) becomes offset + 1
    }

    public synchronized void updateHighWatermark(long newHw) {
        this.highWatermark = Math.min(newHw, log.size());
    }

    public synchronized List<Record> readCommitted(long fromOffset) {
        List<Record> committed = new ArrayList<>();
        for (long i = fromOffset; i < highWatermark && i < log.size(); i++) {
            committed.add(log.get((int) i));
        }
        return committed;
    }

    public long getLeo() { return log.size(); }
    public long getHighWatermark() { return highWatermark; }
}
