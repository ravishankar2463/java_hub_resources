package com.javahub.gateway.service;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class RouteRegistryService {
    public record RouteDefinition(String pathPattern, String upstreamService, boolean requireAuth) {}
    private final List<RouteDefinition> routes = List.of(
        new RouteDefinition("/api/v1/users/**", "http://user-service:8081", true),
        new RouteDefinition("/api/v1/orders/**", "http://order-service:8082", true),
        new RouteDefinition("/api/v1/catalog/**", "http://catalog-service:8083", false)
    );
    public List<RouteDefinition> getRoutes() { return routes; }
}
