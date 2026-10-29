package com.javahub.level15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.ByteBuffer;
import static org.assertj.core.api.Assertions.*;

class GarbageFreeByteBufferPoolTest {

    @Test
    @DisplayName("Verify zero-allocation direct buffer recycling")
    void testBufferAcquisitionAndRelease() {
        GarbageFreeByteBufferPool pool = new GarbageFreeByteBufferPool(1024, 4);

        assertThat(pool.availableCount()).isEqualTo(4);

        ByteBuffer b1 = pool.acquire();
        ByteBuffer b2 = pool.acquire();

        assertThat(pool.availableCount()).isEqualTo(2);

        b1.putInt(12345);
        pool.release(b1);
        assertThat(pool.availableCount()).isEqualTo(3);

        ByteBuffer recycled = pool.acquire();
        assertThat(recycled.position()).isEqualTo(0); // verified cleared on release/acquire
    }
}
