package io.javahub.level12.dockerandcontainerization;

/**
 * Detects whether the JVM is properly honoring cgroup container memory constraints.
 */
public class ContainerCgroupDetector {

    public record MemorySpec(long maxHeapBytes, int availableProcessors) {}

    public MemorySpec getEffectiveRuntimeLimits() {
        return new MemorySpec(
            Runtime.getRuntime().maxMemory(),
            Runtime.getRuntime().availableProcessors()
        );
    }
}
