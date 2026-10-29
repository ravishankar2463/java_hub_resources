package io.javahub.level8.buildingrestfulapis;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

/**
 * RFC 7807 Problem Detail response builder standard in Spring Boot 3.
 */
public class ProblemDetailResponseBuilder {

    public record ProblemDetail(
        URI type,
        String title,
        int status,
        String detail,
        URI instance,
        Instant timestamp,
        Map<String, Object> properties
    ) {
        public static ProblemDetail forNotFound(String entityName, String id) {
            return new ProblemDetail(
                URI.create("https://api.javahub.io/errors/not-found"),
                "Entity Not Found",
                404,
                entityName + " with ID " + id + " was not located",
                URI.create("/api/" + entityName.toLowerCase() + "/" + id),
                Instant.now(),
                Map.of("errorCode", "ERR_404_NOT_FOUND")
            );
        }
    }
}
