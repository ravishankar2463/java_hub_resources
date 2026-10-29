package io.javahub.level10.messagebrokersvseventstreaming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Contrasts queue-based destructive reads against immutable event stream offset retention.
 */
public class BrokerModelSimulator {

    // Queue pattern (RabbitMQ): message deleted after delivery
    public static class DestructiveQueue {
        private final Queue<String> queue = new LinkedList<>();

        public void publish(String msg) { queue.add(msg); }
        public String poll() { return queue.poll(); }
        public int size() { return queue.size(); }
    }

    // Log pattern (Kafka): message retained indefinitely, consumed via offset pointers
    public static class ImmutableLog {
        private final List<String> log = new ArrayList<>();

        public synchronized int append(String msg) {
            log.add(msg);
            return log.size() - 1; // Returns offset
        }

        public synchronized String readAtOffset(int offset) {
            return (offset >= 0 && offset < log.size()) ? log.get(offset) : null;
        }

        public synchronized int totalMessages() { return log.size(); }
    }
}
