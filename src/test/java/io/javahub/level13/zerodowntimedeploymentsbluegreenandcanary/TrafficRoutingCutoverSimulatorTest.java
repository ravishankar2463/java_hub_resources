package io.javahub.level13.zerodowntimedeploymentsbluegreenandcanary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TrafficRoutingCutoverSimulatorTest {

    @Test
    @DisplayName("Should instant flip traffic target from Blue to Green and support instant rollback")
    void testBlueGreenCutover() {
        var router = new TrafficRoutingCutoverSimulator();
        assertThat(router.getActiveTarget()).isEqualTo(TrafficRoutingCutoverSimulator.Environment.BLUE_V1);

        router.instantFlipToGreen();
        assertThat(router.getActiveTarget()).isEqualTo(TrafficRoutingCutoverSimulator.Environment.GREEN_V2);

        router.emergencyRollbackToBlue();
        assertThat(router.getActiveTarget()).isEqualTo(TrafficRoutingCutoverSimulator.Environment.BLUE_V1);
    }
}
