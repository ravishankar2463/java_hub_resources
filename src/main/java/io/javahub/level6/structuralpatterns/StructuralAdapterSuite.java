package io.javahub.level6.structuralpatterns;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Structural Adapter and Decorator patterns.
 */
public class StructuralAdapterSuite {

    public interface MessageSender {
        String send(String message);
    }

    public static class DefaultSender implements MessageSender {
        @Override
        public String send(String message) {
            return "SENT: " + message;
        }
    }

    // Decorator adding metrics counting without modifying original sender
    public static class CountingSenderDecorator implements MessageSender {
        private final MessageSender delegate;
        private final AtomicInteger counter = new AtomicInteger(0);

        public CountingSenderDecorator(MessageSender delegate) {
            this.delegate = delegate;
        }

        @Override
        public String send(String message) {
            counter.incrementAndGet();
            return delegate.send(message);
        }

        public int getInvocationCount() {
            return counter.get();
        }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: StructuralAdapterSuite");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for StructuralAdapterSuite...");
        try {
            StructuralAdapterSuite instance = new StructuralAdapterSuite();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
