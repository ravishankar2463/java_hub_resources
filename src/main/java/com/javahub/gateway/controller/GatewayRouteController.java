package com.javahub.gateway.controller;
import com.javahub.gateway.service.RouteRegistryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayRouteController {
    private final RouteRegistryService routeRegistry;
    public GatewayRouteController(RouteRegistryService routeRegistry) { this.routeRegistry = routeRegistry; }
    @GetMapping("/routes")
    public ResponseEntity<List<RouteRegistryService.RouteDefinition>> listRoutes() { return ResponseEntity.ok(routeRegistry.getRoutes()); }
}
