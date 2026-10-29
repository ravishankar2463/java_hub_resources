package io.javahub.level5.threadsafetyandsynchronization;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Thread-safe bounded queue demonstrating wait/notify condition queues on monitor locks.
 */
public class SynchronizedBoundedQueue<T> {

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SynchronizedBoundedQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Release monitor lock and sleep until notified
        }
        queue.add(item);
        notifyAll(); // Wake up consumers waiting for items
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Release monitor lock and sleep until notified
        }
        T item = queue.remove();
        notifyAll(); // Wake up producers waiting for space
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}
