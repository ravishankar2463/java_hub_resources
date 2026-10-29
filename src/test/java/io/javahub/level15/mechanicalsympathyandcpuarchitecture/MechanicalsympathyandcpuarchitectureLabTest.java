package io.javahub.level15.mechanicalsympathyandcpuarchitecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 15: Mechanical Sympathy and CPU Architecture.
 */
public class MechanicalsympathyandcpuarchitectureLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        MechanicalsympathyandcpuarchitectureLab lab = new MechanicalsympathyandcpuarchitectureLab();

        assertThat(lab.getLevel()).isEqualTo(15);
        assertThat(lab.getTopicTitle()).isEqualTo("Mechanical Sympathy and CPU Architecture");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
