package io.javahub.level11.distributedratelimitingandthrottling;

/**
 * Token Bucket rate limiter implementing burst tolerance and steady-state token refilling.
 */
public class TokenBucketRateLimiter {

    private final long capacity;
    private final double refillRatePerSecond;
    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        refill();
        if (tokens >= 1.0) {
            tokens -= 1.0;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double deltaSeconds = (now - lastRefillTimestamp) / 1000.0;
        tokens = Math.min(capacity, tokens + (deltaSeconds * refillRatePerSecond));
        lastRefillTimestamp = now;
    }
}
