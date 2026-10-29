package io.javahub.level5.threadsafetyandsynchronization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class SynchronizedBoundedQueueTest {

    @Test
    @DisplayName("Should coordinate producer and consumer across threads without race conditions")
    void testProducerConsumer() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            SynchronizedBoundedQueue<Integer> queue = new SynchronizedBoundedQueue<>(2);

            Thread producer = Thread.ofPlatform().start(() -> {
                try {
                    queue.put(100);
                    queue.put(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            producer.join();
            assertThat(queue.size()).isEqualTo(2);

            int val1 = queue.take();
            int val2 = queue.take();

            assertThat(val1).isEqualTo(100);
            assertThat(val2).isEqualTo(200);
            assertThat(queue.size()).isEqualTo(0);
        });
    }
}
