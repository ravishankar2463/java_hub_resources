package io.javahub.level5.projectloomvirtualthreads;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * High-concurrency task processing utilizing Java 21 lightweight Virtual Threads.
 */
public class VirtualThreadDispatcher {

    public int runConcurrentTasks(int taskCount) {
        AtomicInteger counter = new AtomicInteger(0);

        // Java 21 newVirtualThreadPerTaskExecutor creates unpinned lightweight virtual threads
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Runnable> tasks = IntStream.range(0, taskCount)
                .mapToObj(i -> (Runnable) () -> {
                    // Simulates lightweight non-blocking work
                    counter.incrementAndGet();
                })
                .toList();

            tasks.forEach(executor::submit);
        } // AutoCloseable executor joins all virtual threads before exit

        return counter.get();
    }

    public boolean isCurrentThreadVirtual() {
        return Thread.currentThread().isVirtual();
    }
}
