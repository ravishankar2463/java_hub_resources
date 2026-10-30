package com.javahub.pool.controller;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/pool")
public class PoolMetricsController {
    private final DataSource dataSource;
    public PoolMetricsController(DataSource dataSource) { this.dataSource = dataSource; }
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getPoolStatus() {
        if (dataSource instanceof HikariDataSource hikari) {
            return ResponseEntity.ok(Map.of(
                "poolName", hikari.getPoolName(),
                "maximumPoolSize", hikari.getMaximumPoolSize(),
                "minimumIdle", hikari.getMinimumIdle(),
                "activeConnections", hikari.getHikariPoolMXBean().getActiveConnections(),
                "idleConnections", hikari.getHikariPoolMXBean().getIdleConnections(),
                "totalConnections", hikari.getHikariPoolMXBean().getTotalConnections()
            ));
        }
        return ResponseEntity.ok(Map.of("status", "NOT_HIKARI"));
    }
}
