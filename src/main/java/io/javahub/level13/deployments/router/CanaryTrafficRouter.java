package io.javahub.level13.deployments.router;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CanaryTrafficRouter {

    private final AtomicInteger canaryWeightPercentage = new AtomicInteger(10); // 10% canary traffic
    private volatile boolean isCutoverToGreen = false;

    public String routeTraffic() {
        if (isCutoverToGreen) {
            return "GREEN_V2_ACTIVE";
        }

        int diceRoll = ThreadLocalRandom.current().nextInt(100);
        if (diceRoll < canaryWeightPercentage.get()) {
            return "CANARY_V2_EXPERIMENTAL";
        }
        return "BLUE_V1_STABLE";
    }

    public void setCanaryWeight(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Weight must be between 0 and 100");
        }
        this.canaryWeightPercentage.set(percentage);
    }

    public void completeCutover() {
        this.isCutoverToGreen = true;
    }

    public void emergencyRollback() {
        this.canaryWeightPercentage.set(0);
        this.isCutoverToGreen = false;
    }

    public int getCanaryWeight() {
        return canaryWeightPercentage.get();
    }

    public boolean isCutoverToGreen() {
        return isCutoverToGreen;
    }
}
