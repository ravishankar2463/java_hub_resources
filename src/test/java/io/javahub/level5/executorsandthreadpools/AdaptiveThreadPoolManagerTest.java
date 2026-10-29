package io.javahub.level5.executorsandthreadpools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Future;
import static org.assertj.core.api.Assertions.assertThat;

public class AdaptiveThreadPoolManagerTest {

    @Test
    @DisplayName("ThreadPoolExecutor should process submitted tasks and return futures")
    void testExecutorExecution() throws Exception {
        AdaptiveThreadPoolManager manager = new AdaptiveThreadPoolManager(2, 4, 10);

        Future<Integer> f1 = manager.submitCalculation(21);
        Future<Integer> f2 = manager.submitCalculation(50);

        assertThat(f1.get()).isEqualTo(42);
        assertThat(f2.get()).isEqualTo(100);

        manager.shutdown();
    }
}
