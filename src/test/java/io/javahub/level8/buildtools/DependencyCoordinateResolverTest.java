package io.javahub.level8.buildtools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DependencyCoordinateResolverTest {

    @Test
    @DisplayName("Should resolve active dependencies while excluding banned transitive artifacts")
    void testDependencyExclusion() {
        var resolver = new DependencyCoordinateResolver();
        resolver.addDependency(new DependencyCoordinateResolver.Dependency("org.springframework.boot", "spring-boot-starter-web", "3.3.0", false));
        resolver.addDependency(new DependencyCoordinateResolver.Dependency("commons-logging", "commons-logging", "1.2", true)); // Excluded

        assertThat(resolver.getActiveDependencies()).hasSize(1);
    }
}
