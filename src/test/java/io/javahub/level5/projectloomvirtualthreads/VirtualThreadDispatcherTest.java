package io.javahub.level5.projectloomvirtualthreads;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VirtualThreadDispatcherTest {

    private final VirtualThreadDispatcher dispatcher = new VirtualThreadDispatcher();

    @Test
    @DisplayName("Should spawn and complete 10,000 tasks concurrently on Java 21 Virtual Threads")
    void testMassiveVirtualThreadConcurrency() {
        int completed = dispatcher.runConcurrentTasks(10_000);
        assertThat(completed).isEqualTo(10_000);
    }
}
