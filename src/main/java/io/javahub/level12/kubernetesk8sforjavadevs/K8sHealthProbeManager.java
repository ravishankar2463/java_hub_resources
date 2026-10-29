package io.javahub.level12.kubernetesk8sforjavadevs;

/**
 * Emulates Spring Boot Actuator Kubernetes health probe lifecycle.
 */
public class K8sHealthProbeManager {

    private boolean startupComplete = false;
    private boolean dbReachable = true;

    public void completeStartup() { this.startupComplete = true; }
    public void setDbReachable(boolean reachable) { this.dbReachable = reachable; }

    public boolean isStartupProbeHealthy() { return startupComplete; }
    public boolean isLivenessProbeHealthy() { return startupComplete; } // Fails only on deadlock
    public boolean isReadinessProbeHealthy() { return startupComplete && dbReachable; } // Pulls pod from service endpoints if DB down
}
