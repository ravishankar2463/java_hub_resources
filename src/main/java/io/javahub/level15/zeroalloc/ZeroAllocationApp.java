package io.javahub.level15.zeroalloc;

import com.sun.management.ThreadMXBean;
import java.lang.management.ManagementFactory;

public class ZeroAllocationApp {

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("  Level 15: Zero-Allocation Java & Garbage-Free Flyweights");
        System.out.println("=========================================================\n");

        ThreadMXBean threadBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        long threadId = Thread.currentThread().threadId();

        MarketDataFlyweight flyweight = new MarketDataFlyweight();
        byte[] rawWireBuffer = new byte[64];

        // Warm up JIT
        for (int i = 0; i < 100_000; i++) {
            flyweight.wrap(rawWireBuffer, 0);
            flyweight.setSymbolId(101);
            flyweight.setBidPriceMicros(150_250_000L);
        }

        // Measure allocated bytes
        long startBytes = threadBean.getThreadAllocatedBytes(threadId);

        for (int i = 0; i < 1_000_000; i++) {
            flyweight.wrap(rawWireBuffer, 0);
            flyweight.setSymbolId(101);
            flyweight.setBidPriceMicros(150_250_000L + i);
            long readBid = flyweight.getBidPriceMicros();
        }

        long endBytes = threadBean.getThreadAllocatedBytes(threadId);
        long totalAllocated = endBytes - startBytes;

        System.out.println("Processed 1,000,000 market updates through mutable Flyweight.");
        System.out.println("Total Heap Allocated Bytes: " + totalAllocated + " bytes (GC Free!)");
    }
}
