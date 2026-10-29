package com.javahub.events.listener;

import com.javahub.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class OrderNotificationListener {

    private static final Logger log = LoggerFactory.getLogger(OrderNotificationListener.class);
    private final ConcurrentLinkedQueue<String> eventLog = new ConcurrentLinkedQueue<>();

    @EventListener
    public void onOrderCreatedSync(OrderCreatedEvent event) {
        String msg = "[SYNC_EVENT] Sent confirmation email to " + event.customerEmail() + " for order " + event.orderId();
        log.info(msg);
        eventLog.add(msg);
    }

    @Async
    @EventListener
    public void onOrderCreatedAsync(OrderCreatedEvent event) {
        String msg = "[ASYNC_EVENT] Dispatched warehouse fulfillment job for order " + event.orderId();
        log.info(msg);
        eventLog.add(msg);
    }

    public ConcurrentLinkedQueue<String> getEventLog() {
        return eventLog;
    }
}
