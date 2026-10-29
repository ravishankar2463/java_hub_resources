package io.javahub.level0.environmentsetup;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.List;

/**
 * Diagnostic utility demonstrating JVM architecture inspection at runtime.
 */
public class JdkEnvironmentDiagnostics {

    public record JvmSpecs(
        String javaVersion,
        String javaVendor,
        String osArch,
        int availableProcessors,
        long maxHeapMemoryBytes,
        List<String> garbageCollectorNames
    ) {}

    public JvmSpecs captureCurrentSpecs() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        List<String> gcNames = ManagementFactory.getGarbageCollectorMXBeans()
                .stream()
                .map(gc -> gc.getName())
                .toList();

        return new JvmSpecs(
            System.getProperty("java.version"),
            System.getProperty("java.vendor"),
            System.getProperty("os.arch"),
            Runtime.getRuntime().availableProcessors(),
            memoryBean.getHeapMemoryUsage().getMax(),
            gcNames
        );
    }

    public boolean isJava21OrHigher() {
        String version = System.getProperty("java.version");
        int majorVersion = Runtime.version().feature();
        return majorVersion >= 21;
    }
}
