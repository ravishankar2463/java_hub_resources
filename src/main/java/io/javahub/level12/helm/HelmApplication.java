package io.javahub.level12.helm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class HelmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelmApplication.class, args);
    }

    @GetMapping("/api/v1/helm/info")
    public Map<String, String> getInfo() {
        return Map.of("service", "helm-managed-service", "version", "1.0.0", "status", "UP");
    }
}
