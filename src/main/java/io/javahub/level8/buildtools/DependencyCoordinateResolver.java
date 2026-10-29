package io.javahub.level8.buildtools;

import java.util.HashSet;
import java.util.Set;

/**
 * Models Maven GAV (GroupId, ArtifactId, Version) coordinates and exclusions.
 */
public class DependencyCoordinateResolver {

    public record Dependency(String groupId, String artifactId, String version, boolean excluded) {}

    private final Set<Dependency> dependencies = new HashSet<>();

    public void addDependency(Dependency dep) {
        if (!dep.excluded()) {
            dependencies.add(dep);
        }
    }

    public Set<Dependency> getActiveDependencies() {
        return Set.copyOf(dependencies);
    }
}
