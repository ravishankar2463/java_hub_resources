package io.javahub.level11.apigatewaysandservicediscovery;

import java.util.List;

/**
 * Emulates Spring Cloud Gateway path matching and upstream round-robin load balancing.
 */
public class ApiGatewayRouteMatcher {

    private final List<String> targetInstances = List.of("http://10.0.1.5:8080", "http://10.0.1.6:8080");
    private int counter = 0;

    public synchronized String routeToNextInstance(String requestPath) {
        if (!requestPath.startsWith("/api/orders")) {
            throw new IllegalArgumentException("No matching gateway route predicate for: " + requestPath);
        }
        String instance = targetInstances.get(counter % targetInstances.size());
        counter++;
        return instance + requestPath;
    }
}
