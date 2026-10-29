package com.javahub.level15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LmaxDisruptorRingBufferTest {

    @Test
    @DisplayName("Verify lock-free ring buffer sequencing and bitwise masking")
    void testDisruptorRingBuffer() {
        LmaxDisruptorRingBuffer<String> ringBuffer = new LmaxDisruptorRingBuffer<>(1024);

        long seq0 = ringBuffer.next();
        ringBuffer.publish(seq0, "ORDER_CREATED_0");

        long seq1 = ringBuffer.next();
        ringBuffer.publish(seq1, "ORDER_MATCHED_1");

        assertThat(ringBuffer.get(seq0)).isEqualTo("ORDER_CREATED_0");
        assertThat(ringBuffer.get(seq1)).isEqualTo("ORDER_MATCHED_1");
        assertThat(ringBuffer.getCursor()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Verify wrap-around masking behavior over buffer bounds")
    void testWrapAround() {
        LmaxDisruptorRingBuffer<Integer> buffer = new LmaxDisruptorRingBuffer<>(4);

        for (int i = 0; i < 8; i++) {
            long seq = buffer.next();
            buffer.publish(seq, i * 100);
        }

        // Sequences 4, 5, 6, 7 overwrite positions 0, 1, 2, 3
        assertThat(buffer.get(4)).isEqualTo(400);
        assertThat(buffer.get(7)).isEqualTo(700);
        assertThat(buffer.getCursor()).isEqualTo(7L);
    }
}
