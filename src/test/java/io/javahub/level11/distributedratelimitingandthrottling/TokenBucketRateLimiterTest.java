package io.javahub.level11.distributedratelimitingandthrottling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TokenBucketRateLimiterTest {

    @Test
    @DisplayName("Token bucket should allow burst up to capacity and reject when tokens exhausted")
    void testTokenBucketBurst() {
        var limiter = new TokenBucketRateLimiter(2, 1.0); // Capacity 2, 1 token/sec

        assertThat(limiter.tryAcquire()).isTrue();
        assertThat(limiter.tryAcquire()).isTrue();
        assertThat(limiter.tryAcquire()).isFalse(); // Exhausted
    }
}
