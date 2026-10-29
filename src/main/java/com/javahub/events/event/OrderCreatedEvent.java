package com.javahub.events.event;

public record OrderCreatedEvent(String orderId, String customerEmail, double amount) {}
