package com.javahub.buildtools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/build")
public class BuildMetadataController {

    private final Optional<BuildProperties> buildProperties;

    @Autowired
    public BuildMetadataController(Optional<BuildProperties> buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getBuildInfo() {
        Map<String, Object> info = new HashMap<>();
        if (buildProperties.isPresent()) {
            BuildProperties bp = buildProperties.get();
            info.put("artifact", bp.getArtifact());
            info.put("group", bp.getGroup());
            info.put("version", bp.getVersion());
            info.put("buildTime", bp.getTime());
            info.put("status", "SUCCESSFUL_BUILD");
        } else {
            info.put("artifact", "build-tools-app");
            info.put("status", "DEVELOPMENT_RUNTIME");
            info.put("javaVersion", System.getProperty("java.version"));
        }
        return ResponseEntity.ok(info);
    }
}
