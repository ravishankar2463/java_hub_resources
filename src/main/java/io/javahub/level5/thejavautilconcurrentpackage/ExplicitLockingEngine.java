package io.javahub.level5.thejavautilconcurrentpackage;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.StampedLock;

/**
 * Demonstrates explicit locks, StampedLock optimistic reads, and high-contention LongAdder.
 */
public class ExplicitLockingEngine {

    private final StampedLock stampedLock = new StampedLock();
    private double x = 1.0;
    private double y = 2.0;

    private final LongAdder metricsCounter = new LongAdder();

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
            metricsCounter.increment();
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double calculateDistanceFromOrigin() {
        // Step 1: Optimistic read without acquiring an exclusive lock
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;

        // Step 2: Validate if a writer intervened
        if (!stampedLock.validate(stamp)) {
            // Fall back to pessimistic read lock
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public long getTotalOperations() {
        return metricsCounter.sum();
    }
}
