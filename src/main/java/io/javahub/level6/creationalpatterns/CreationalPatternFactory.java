package io.javahub.level6.creationalpatterns;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Factory Method and Object Pool creational design patterns.
 */
public class CreationalPatternFactory {

    public interface Transport {
        String deliver();
    }

    public static class DroneTransport implements Transport {
        @Override
        public String deliver() { return "Delivered via autonomous drone"; }
    }

    public static class TruckTransport implements Transport {
        @Override
        public String deliver() { return "Delivered via overland freight"; }
    }

    // Object Pool managing reusable expensive resources
    public static class TransportPool {
        private final BlockingQueue<Transport> pool;

        public TransportPool(int capacity) {
            pool = new ArrayBlockingQueue<>(capacity);
            for (int i = 0; i < capacity; i++) {
                pool.add(new DroneTransport());
            }
        }

        public Transport borrowTransport() throws InterruptedException {
            return pool.take();
        }

        public void returnTransport(Transport transport) {
            pool.offer(transport);
        }

        public int availableCount() {
            return pool.size();
        }
    }
}
