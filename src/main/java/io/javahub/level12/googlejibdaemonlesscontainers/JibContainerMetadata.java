package io.javahub.level12.googlejibdaemonlesscontainers;

import java.util.List;

/**
 * Models Google Jib reproducible container layer splitting.
 */
public class JibContainerMetadata {

    public List<String> getContainerLayers() {
        return List.of(
            "layer-1: dependencies (cached across all builds)",
            "layer-2: resources (application configuration)",
            "layer-3: classes (frequently modified bytecode)"
        );
    }
}
