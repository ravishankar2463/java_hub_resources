package io.javahub.level12.docker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/container")
public class ContainerDiagnosticsController {

    @GetMapping("/diagnostics")
    public ResponseEntity<Map<String, Object>> getContainerDiagnostics() {
        Map<String, Object> diagnostics = new LinkedHashMap<>();
        Runtime runtime = Runtime.getRuntime();

        // JVM runtime memory detection
        diagnostics.put("jvmAvailableProcessors", runtime.availableProcessors());
        diagnostics.put("jvmMaxMemoryMb", runtime.maxMemory() / (1024 * 1024));
        diagnostics.put("jvmTotalMemoryMb", runtime.totalMemory() / (1024 * 1024));
        diagnostics.put("jvmFreeMemoryMb", runtime.freeMemory() / (1024 * 1024));

        // Linux cgroup detection (v1 / v2)
        boolean hasCgroupV2 = new File("/sys/fs/cgroup/cgroup.controllers").exists();
        boolean hasDockerEnv = new File("/.dockerenv").exists();
        diagnostics.put("isDockerContainer", hasDockerEnv);
        diagnostics.put("cgroupV2Detected", hasCgroupV2);

        // Read cgroup memory limit if available
        try {
            Path cgroupV2Mem = Path.of("/sys/fs/cgroup/memory.max");
            if (Files.exists(cgroupV2Mem)) {
                diagnostics.put("cgroupV2MemoryMax", Files.readString(cgroupV2Mem).trim());
            }
        } catch (Exception ignored) {
        }

        diagnostics.put("status", "ACTIVE_AND_CONSTRAINED");
        return ResponseEntity.ok(diagnostics);
    }
}
