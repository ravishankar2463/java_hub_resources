package io.javahub.level15.panama;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PanamaTest {

    @Test
    @DisplayName("Verify Project Panama off-heap read and writes with deterministic Arena lifecycle")
    void shouldStoreAndRetrieveOffHeapLongs() {
        try (OffHeapBufferManager manager = new OffHeapBufferManager(256)) {
            manager.put(0, 123456789L);
            manager.put(255, 987654321L);

            assertThat(manager.get(0)).isEqualTo(123456789L);
            assertThat(manager.get(255)).isEqualTo(987654321L);
            assertThat(manager.getByteSize()).isEqualTo(256 * 8);
        }
    }
}
