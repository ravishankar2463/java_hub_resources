package io.javahub.level11.distributedtransactions;

import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates distributed sagas executing compensating transactions upon step failure.
 */
public class SagaOrchestrator {

    public interface SagaStep {
        String name();
        boolean execute();
        void compensate();
    }

    public boolean runSaga(List<SagaStep> steps, List<String> compensationLog) {
        List<SagaStep> executedSteps = new ArrayList<>();

        for (SagaStep step : steps) {
            boolean success = step.execute();
            if (!success) {
                // Failure detected: Roll back all previously executed steps in reverse order
                for (int i = executedSteps.size() - 1; i >= 0; i--) {
                    executedSteps.get(i).compensate();
                    compensationLog.add("COMPENSATED: " + executedSteps.get(i).name());
                }
                return false;
            }
            executedSteps.add(step);
        }
        return true;
    }
}
