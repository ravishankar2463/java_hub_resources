package io.javahub.level7.distributedcachingwithredis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

public class RedisCacheAsideEngineTest {

    @Test
    @DisplayName("Cache-Aside pattern should only hit database loader on cache miss")
    void testCacheAside() {
        var cache = new RedisCacheAsideEngine<String, String>();
        AtomicInteger dbHits = new AtomicInteger(0);

        String v1 = cache.getOrLoad("user_1", k -> { dbHits.incrementAndGet(); return "Alice"; });
        String v2 = cache.getOrLoad("user_1", k -> { dbHits.incrementAndGet(); return "Alice"; });

        assertThat(v1).isEqualTo("Alice");
        assertThat(v2).isEqualTo("Alice");
        assertThat(dbHits.get()).isEqualTo(1); // Loaded from DB exactly once
    }
}
