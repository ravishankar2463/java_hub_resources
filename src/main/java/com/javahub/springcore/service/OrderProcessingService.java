package com.javahub.springcore.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService {
    private static final Logger log = LoggerFactory.getLogger(OrderProcessingService.class);

    @PostConstruct
    public void init() {
        log.info(">> [LIFECYCLE] OrderProcessingService initialized via @PostConstruct");
    }

    @PreDestroy
    public void cleanup() {
        log.info(">> [LIFECYCLE] OrderProcessingService tearing down via @PreDestroy");
    }

    public String processOrder(String orderId) {
        return "ORDER_PROCESSED: " + orderId;
    }
}
