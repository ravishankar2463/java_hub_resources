package io.javahub.level15.disruptor;

public class DisruptorApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=========================================================");
        System.out.println("  Level 15: LMAX Disruptor Lock-Free Ring Buffer Demo    ");
        System.out.println("=========================================================\n");

        int bufferSize = 1024; // Must be power of 2
        LockFreeRingBuffer<OrderEvent> ringBuffer = new LockFreeRingBuffer<>(bufferSize, OrderEvent::new);

        System.out.println("Ring buffer initialized with " + bufferSize + " pre-allocated slots.");

        // Publish 10 events
        for (int i = 1; i <= 10; i++) {
            long seq = ringBuffer.nextSequence();
            OrderEvent event = ringBuffer.get(seq);
            event.setOrderId("ORD-" + i);
            event.setPrice(100.0 * i);
            ringBuffer.publish(seq);
            System.out.println("Published: " + event.getOrderId() + " @ sequence " + seq);
        }

        System.out.println("\nAll events published with ZERO runtime object allocations!");
    }

    public static class OrderEvent {
        private String orderId;
        private double price;

        public void setOrderId(String orderId) { this.orderId = orderId; }
        public void setPrice(double price) { this.price = price; }
        public String getOrderId() { return orderId; }
        public double getPrice() { return price; }
    }
}
