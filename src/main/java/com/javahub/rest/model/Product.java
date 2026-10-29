package com.javahub.rest.model;

import java.time.Instant;

public record Product(
    String id,
    String name,
    String sku,
    double price,
    Instant createdAt
) {}
