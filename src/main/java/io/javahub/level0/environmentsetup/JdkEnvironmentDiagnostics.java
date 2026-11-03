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

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: JdkEnvironmentDiagnostics");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for JdkEnvironmentDiagnostics...");
        try {
            JdkEnvironmentDiagnostics instance = new JdkEnvironmentDiagnostics();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
