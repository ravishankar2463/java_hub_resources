package io.javahub.level12.jib.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/jib")
public class ImageLayerController {

    @GetMapping("/layers")
    public ResponseEntity<Map<String, Object>> getLayeringArchitecture() {
        return ResponseEntity.ok(Map.of(
                "buildEngine", "Google Jib Maven Plugin",
                "daemonRequired", false,
                "layers", List.of(
                        Map.of("layer", 1, "type", "Base Image", "source", "eclipse-temurin:21-jre-alpine"),
                        Map.of("layer", 2, "type", "Dependencies", "description", "Fast-cached external JARs from Maven Central"),
                        Map.of("layer", 3, "type", "Resources", "description", "application.yml and static assets"),
                        Map.of("layer", 4, "type", "Application Classes", "description", "Fastest changing layer - rebuilt in seconds")
                ),
                "benefits", List.of(
                        "No Docker daemon required in CI runner",
                        "Reproducible bit-for-bit builds",
                        "Blazing fast push times due to layered caching"
                )
        ));
    }
}
