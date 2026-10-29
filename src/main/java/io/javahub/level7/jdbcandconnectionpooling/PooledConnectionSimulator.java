package io.javahub.level7.jdbcandconnectionpooling;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Emulates HikariCP connection leasing and auto-closing.
 */
public class PooledConnectionSimulator {

    public record MockConnection(int id) implements AutoCloseable {
        @Override
        public void close() {
            // Emulates returning leased connection to pool
        }
    }

    private final BlockingQueue<MockConnection> pool = new ArrayBlockingQueue<>(5);

    public PooledConnectionSimulator() {
        for (int i = 1; i <= 5; i++) {
            pool.add(new MockConnection(i));
        }
    }

    public MockConnection lease() throws InterruptedException {
        return pool.take();
    }

    public void release(MockConnection conn) {
        pool.offer(conn);
    }

    public int availableCount() { return pool.size(); }
}
