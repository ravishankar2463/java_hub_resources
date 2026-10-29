package io.javahub.level10.resilienteventprocessinganddeadletterqueues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeadLetterQueueDispatcherTest {

    @Test
    @DisplayName("Should route poison pill message to Dead Letter Queue after max retries")
    void testDlqRouting() {
        var dispatcher = new DeadLetterQueueDispatcher();

        dispatcher.processMessage("retryable_msg", 1, 3);
        dispatcher.processMessage("poison_pill_msg", 3, 3);

        assertThat(dispatcher.getMainQueue()).containsExactly("retryable_msg");
        assertThat(dispatcher.getDeadLetterQueue()).containsExactly("poison_pill_msg");
    }
}
