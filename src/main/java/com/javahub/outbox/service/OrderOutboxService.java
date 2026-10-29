package com.javahub.outbox.service;
import com.javahub.outbox.entity.OutboxEvent;
import com.javahub.outbox.repository.OutboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class OrderOutboxService {
    private static final Logger log = LoggerFactory.getLogger(OrderOutboxService.class);
    private final OutboxRepository outboxRepository;
    public OrderOutboxService(OutboxRepository outboxRepository) { this.outboxRepository = outboxRepository; }
    @Transactional
    public OutboxEvent createOrderWithOutbox(String orderId, double amount) {
        OutboxEvent event = new OutboxEvent("ORDER", orderId, "OrderCreated", "{\"orderId\": \"" + orderId + "\", \"amount\": " + amount + "}");
        return outboxRepository.save(event);
    }
    @Scheduled(fixedRate = 2000)
    @Transactional
    public void relayOutboxEvents() {
        List<OutboxEvent> pending = outboxRepository.findByStatus("PENDING");
        for (OutboxEvent event : pending) {
            log.info(">> [OUTBOX RELAY] Publishing event {} to Kafka", event.getEventType());
            event.setStatus("PUBLISHED");
            outboxRepository.save(event);
        }
    }
    public List<OutboxEvent> getAllEvents() { return outboxRepository.findAll(); }
}
