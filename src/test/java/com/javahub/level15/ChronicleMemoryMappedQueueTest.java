package com.javahub.level15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ChronicleMemoryMappedQueueTest {

    @Test
    @DisplayName("Verify memory-mapped binary IPC queue write and read semantics")
    void testMemoryMappedQueue() {
        ChronicleMemoryMappedQueue queue = new ChronicleMemoryMappedQueue(4096);

        boolean w1 = queue.writeMessage("EXEC_REPORT_FX_USD_EUR_100K");
        boolean w2 = queue.writeMessage("ORDER_CANCEL_ACK_7721");

        assertThat(w1).isTrue();
        assertThat(w2).isTrue();
        assertThat(queue.unreadBytes()).isGreaterThan(0);

        String msg1 = queue.readNextMessage();
        String msg2 = queue.readNextMessage();
        String msg3 = queue.readNextMessage();

        assertThat(msg1).isEqualTo("EXEC_REPORT_FX_USD_EUR_100K");
        assertThat(msg2).isEqualTo("ORDER_CANCEL_ACK_7721");
        assertThat(msg3).isNull();
        assertThat(queue.unreadBytes()).isEqualTo(0);
    }
}
