package io.javahub.level8.buildingrestfulapis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Building RESTful APIs.
 */
public class BuildingrestfulapisLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        BuildingrestfulapisLab lab = new BuildingrestfulapisLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Building RESTful APIs");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
