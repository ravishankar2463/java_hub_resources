package io.javahub.level12.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class CiCdApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiCdApplication.class, args);
    }

    @GetMapping("/api/v1/cicd/status")
    public Map<String, Object> getPipelineStatus() {
        return Map.of(
                "pipeline", "GitHub Actions",
                "infrastructure", "Terraform HCL",
                "qualityGate", "PASSED",
                "timestamp", System.currentTimeMillis()
        );
    }
}
