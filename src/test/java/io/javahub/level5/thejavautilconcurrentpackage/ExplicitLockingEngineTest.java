package io.javahub.level5.thejavautilconcurrentpackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class ExplicitLockingEngineTest {

    @Test
    @DisplayName("Should perform optimistic read and update coordinate state safely")
    void testStampedLockAndLongAdder() {
        ExplicitLockingEngine engine = new ExplicitLockingEngine();

        engine.move(2.0, 2.0); // Now at (3.0, 4.0) -> distance should be 5.0
        double dist = engine.calculateDistanceFromOrigin();

        assertThat(dist).isCloseTo(5.0, within(0.001));
        assertThat(engine.getTotalOperations()).isEqualTo(1L);
    }
}
