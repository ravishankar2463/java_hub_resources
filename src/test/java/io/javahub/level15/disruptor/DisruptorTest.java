package io.javahub.level15.disruptor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DisruptorTest {

    @Test
    @DisplayName("Verify bitwise index wrapping on power of two ring buffer")
    void shouldWrapIndexCorrectly() {
        LockFreeRingBuffer<DisruptorApp.OrderEvent> buffer =
                new LockFreeRingBuffer<>(16, DisruptorApp.OrderEvent::new);

        for (int i = 0; i < 32; i++) {
            long seq = buffer.nextSequence();
            DisruptorApp.OrderEvent event = buffer.get(seq);
            event.setOrderId("ORDER-" + i);
            assertThat(event.getOrderId()).isEqualTo("ORDER-" + i);
        }
    }
}
