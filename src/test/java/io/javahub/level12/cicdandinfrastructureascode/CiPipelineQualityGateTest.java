package io.javahub.level12.cicdandinfrastructureascode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CiPipelineQualityGateTest {

    @Test
    @DisplayName("Quality gate should pass only with 0 failed tests and adequate code coverage")
    void testQualityGate() {
        var gate = new CiPipelineQualityGate();

        var passing = new CiPipelineQualityGate.BuildReport(100, 0, 85.5);
        var failingTests = new CiPipelineQualityGate.BuildReport(100, 1, 90.0);
        var lowCoverage = new CiPipelineQualityGate.BuildReport(100, 0, 65.0);

        assertThat(gate.passesQualityGate(passing)).isTrue();
        assertThat(gate.passesQualityGate(failingTests)).isFalse();
        assertThat(gate.passesQualityGate(lowCoverage)).isFalse();
    }
}
