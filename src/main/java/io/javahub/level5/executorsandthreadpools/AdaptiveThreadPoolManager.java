package io.javahub.level5.executorsandthreadpools;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolExecutor with bounded queue and custom rejection policy tracking dropped tasks.
 */
public class AdaptiveThreadPoolManager {

    private final ThreadPoolExecutor executor;
    private final AtomicInteger rejectedCount = new AtomicInteger(0);

    public AdaptiveThreadPoolManager(int coreSize, int maxSize, int queueCapacity) {
        this.executor = new ThreadPoolExecutor(
            coreSize,
            maxSize,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(queueCapacity),
            new ThreadPoolExecutor.CallerRunsPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    rejectedCount.incrementAndGet();
                    super.rejectedExecution(r, e);
                }
            }
        );
    }

    public Future<Integer> submitCalculation(int value) {
        return executor.submit(() -> value * 2);
    }

    public int getRejectedCount() {
        return rejectedCount.get();
    }

    public void shutdown() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
    }
}
