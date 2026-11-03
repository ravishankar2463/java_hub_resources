package io.javahub.level15.zeroalloc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Flyweight pattern wrapper that reads/writes directly into pre-allocated byte buffers
 * without instantiating any objects during parsing.
 */
public class MarketDataFlyweight {

    private byte[] buffer;
    private int offset;

    public void wrap(byte[] buffer, int offset) {
        this.buffer = buffer;
        this.offset = offset;
    }

    public void setSymbolId(int symbolId) {
        buffer[offset]     = (byte) (symbolId >>> 24);
        buffer[offset + 1] = (byte) (symbolId >>> 16);
        buffer[offset + 2] = (byte) (symbolId >>> 8);
        buffer[offset + 3] = (byte) (symbolId);
    }

    public int getSymbolId() {
        return ((buffer[offset] & 0xFF) << 24) |
               ((buffer[offset + 1] & 0xFF) << 16) |
               ((buffer[offset + 2] & 0xFF) << 8) |
               (buffer[offset + 3] & 0xFF);
    }

    public void setBidPriceMicros(long price) {
        for (int i = 7; i >= 0; i--) {
            buffer[offset + 4 + i] = (byte) (price & 0xFF);
            price >>= 8;
        }
    }

    public long getBidPriceMicros() {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result = (result << 8) | (buffer[offset + 4 + i] & 0xFF);
        }
        return result;
    }
}
