package io.javahub.level2.moderncoreapis;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Modern Java 21 core APIs: Records, java.time, and immutable collections.
 */
public class ModernCoreCatalog {

    public record AuditLog(String actor, Instant timestamp, String action) {}

    public List<AuditLog> createSampleLogs() {
        Instant now = Instant.now();
        // List.of returns an unmodifiable List rejecting nulls and mutations
        return List.of(
            new AuditLog("alice", now, "LOGIN"),
            new AuditLog("bob", now.plus(Duration.ofMinutes(5)), "EXPORT_DATA")
        );
    }

    public ZonedDateTime convertToZone(Instant instant, String zoneIdString) {
        return instant.atZone(ZoneId.of(zoneIdString));
    }
}
