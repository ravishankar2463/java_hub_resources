package io.javahub.level15.zeroalloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ZeroAllocationTest {

    @Test
    @DisplayName("Verify MarketDataFlyweight correctly serializes and deserializes values in-place")
    void shouldOperateFlyweightCorrectly() {
        MarketDataFlyweight flyweight = new MarketDataFlyweight();
        byte[] backingStore = new byte[32];
        flyweight.wrap(backingStore, 0);

        flyweight.setSymbolId(999);
        flyweight.setBidPriceMicros(125_500_000L);

        assertThat(flyweight.getSymbolId()).isEqualTo(999);
        assertThat(flyweight.getBidPriceMicros()).isEqualTo(125_500_000L);
    }
}
