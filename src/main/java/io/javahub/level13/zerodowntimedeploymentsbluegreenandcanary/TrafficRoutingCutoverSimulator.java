package io.javahub.level13.zerodowntimedeploymentsbluegreenandcanary;

/**
 * Traffic router for zero-downtime Blue/Green cutovers and Canary percentage splits.
 */
public class TrafficRoutingCutoverSimulator {

    public enum Environment { BLUE_V1, GREEN_V2 }

    private Environment activeTarget = Environment.BLUE_V1;

    public void instantFlipToGreen() {
        this.activeTarget = Environment.GREEN_V2;
    }

    public void emergencyRollbackToBlue() {
        this.activeTarget = Environment.BLUE_V1;
    }

    public Environment getActiveTarget() {
        return activeTarget;
    }
}
