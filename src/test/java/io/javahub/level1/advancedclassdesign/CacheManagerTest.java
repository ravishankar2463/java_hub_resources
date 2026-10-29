package io.javahub.level1.advancedclassdesign;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheManagerTest {

    @Test
    @DisplayName("Static nested CacheEntry should store and retrieve values cleanly")
    void testStaticNestedCache() {
        CacheManager manager = new CacheManager();
        manager.put("session_101", "active_payload");

        assertThat(manager.get("session_101")).isEqualTo("active_payload");
    }
}
