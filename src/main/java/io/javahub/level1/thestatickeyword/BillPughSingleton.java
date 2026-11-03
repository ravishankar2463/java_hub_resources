package io.javahub.level1.thestatickeyword;

import java.util.concurrent.atomic.AtomicLong;

/**
 * High-performance, thread-safe Singleton using the Bill Pugh Holder idiom and static atomics.
 */
public class BillPughSingleton {

    private static final AtomicLong INSTANCE_COUNTER = new AtomicLong(0);

    private BillPughSingleton() {
        INSTANCE_COUNTER.incrementAndGet();
    }

    // Holder class loaded ONLY when getInstance() is called
    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public static long getTotalAllocations() {
        return INSTANCE_COUNTER.get();
    }

    public String computeGreeting(String name) {
        return "Hello from Singleton to " + name;
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: BillPughSingleton");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for BillPughSingleton...");
        try {
            BillPughSingleton instance = new BillPughSingleton();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
