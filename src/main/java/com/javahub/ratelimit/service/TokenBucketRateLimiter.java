package com.javahub.ratelimit.service;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;
@Service
public class TokenBucketRateLimiter {
    private final int capacity = 5;
    private final AtomicInteger tokens = new AtomicInteger(capacity);
    public synchronized boolean tryAcquire() {
        if (tokens.get() > 0) {
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }
    public synchronized void refill() { tokens.set(capacity); }
    public int availableTokens() { return tokens.get(); }
}
