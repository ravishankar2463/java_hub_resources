package com.javahub.events.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductPriceCacheService {

    private static final Logger log = LoggerFactory.getLogger(ProductPriceCacheService.class);
    private final AtomicInteger databaseQueryCounter = new AtomicInteger(0);

    @Cacheable(value = "prices", key = "#sku")
    public double getPriceForSku(String sku) {
        databaseQueryCounter.incrementAndGet();
        log.info(">> [CACHE MISS] Fetching price from database for SKU: {}", sku);
        try {
            Thread.sleep(100); // simulate slow database query
        } catch (InterruptedException ignored) {}
        return 199.95;
    }

    @CacheEvict(value = "prices", allEntries = true)
    public void evictAllPrices() {
        log.info(">> [CACHE EVICT] Invalidated entire prices cache");
    }

    public int getQueryCount() {
        return databaseQueryCounter.get();
    }
}
