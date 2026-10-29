package io.javahub.level11.distributedtransactions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class SagaOrchestratorTest {

    @Test
    @DisplayName("Saga should invoke compensating transactions when a downstream step fails")
    void testSagaCompensations() {
        var orchestrator = new SagaOrchestrator();
        List<String> audit = new ArrayList<>();

        var step1 = new SagaOrchestrator.SagaStep() {
            public String name() { return "ReserveStock"; }
            public boolean execute() { return true; }
            public void compensate() { audit.add("RELEASE_STOCK"); }
        };

        var step2 = new SagaOrchestrator.SagaStep() {
            public String name() { return "DebitPayment"; }
            public boolean execute() { return false; } // Payment fails
            public void compensate() { audit.add("REFUND_PAYMENT"); }
        };

        boolean result = orchestrator.runSaga(List.of(step1, step2), audit);

        assertThat(result).isFalse();
        assertThat(audit).contains("RELEASE_STOCK", "COMPENSATED: ReserveStock");
    }
}
