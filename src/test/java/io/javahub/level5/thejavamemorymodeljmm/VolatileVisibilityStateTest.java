package io.javahub.level5.thejavamemorymodeljmm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VolatileVisibilityStateTest {

    @Test
    @DisplayName("Volatile release-acquire pair must guarantee published payload visibility")
    void testVolatileHappensBefore() {
        VolatileVisibilityState state = new VolatileVisibilityState();

        assertThat(state.readData()).isEqualTo(-1);

        state.writeData(42);
        assertThat(state.isReady()).isTrue();
        assertThat(state.readData()).isEqualTo(42);
    }
}
