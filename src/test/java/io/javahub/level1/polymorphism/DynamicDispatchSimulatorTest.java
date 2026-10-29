package io.javahub.level1.polymorphism;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class DynamicDispatchSimulatorTest {

    @Test
    @DisplayName("Should aggregate areas through dynamic vtable method dispatch")
    void testDynamicDispatch() {
        DynamicDispatchSimulator simulator = new DynamicDispatchSimulator();
        DynamicDispatchSimulator.Shape[] shapes = {
            new DynamicDispatchSimulator.Circle(10),
            new DynamicDispatchSimulator.Rectangle(4, 5)
        };

        double totalArea = simulator.aggregateAreas(shapes);
        double expected = (Math.PI * 100) + 20;

        assertThat(totalArea).isCloseTo(expected, within(0.001));
    }
}
