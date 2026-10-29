package io.javahub.level13.jvmprofilingwithjfrandasyncprofiler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JfrEventSimulatorTest {

    @Test
    @DisplayName("JFR event simulator should capture custom execution events")
    void testJfrEvents() {
        var jfr = new JfrEventSimulator();
        jfr.emitEvent("OrderCheckoutExecution", 12L);

        assertThat(jfr.getRecordedEvents()).hasSize(1);
        assertThat(jfr.getRecordedEvents().get(0).name()).isEqualTo("OrderCheckoutExecution");
    }
}
