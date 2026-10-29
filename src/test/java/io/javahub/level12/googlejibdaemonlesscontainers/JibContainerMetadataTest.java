package io.javahub.level12.googlejibdaemonlesscontainers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JibContainerMetadataTest {

    @Test
    @DisplayName("Jib layer strategy should prioritize heavy dependencies for caching")
    void testLayerStrategy() {
        var jib = new JibContainerMetadata();
        assertThat(jib.getContainerLayers()).hasSize(3);
        assertThat(jib.getContainerLayers().get(0)).contains("dependencies");
    }
}
