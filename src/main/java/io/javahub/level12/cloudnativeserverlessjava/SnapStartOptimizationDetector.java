package io.javahub.level12.cloudnativeserverlessjava;

/**
 * Models AWS Lambda SnapStart / CRaC (Coordinated Restore at Checkpoint) initialization.
 */
public class SnapStartOptimizationDetector {

    private boolean preWarmed = false;

    public void beforeCheckpoint() {
        // Pre-warm class loading and database connections prior to snapshot
        this.preWarmed = true;
    }

    public boolean isOptimizedForInstantRestore() {
        return preWarmed;
    }
}
