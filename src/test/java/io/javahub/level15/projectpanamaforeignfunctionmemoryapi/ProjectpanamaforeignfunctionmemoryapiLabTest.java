package io.javahub.level15.projectpanamaforeignfunctionmemoryapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 15: Project Panama: Foreign Function & Memory API.
 */
public class ProjectpanamaforeignfunctionmemoryapiLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ProjectpanamaforeignfunctionmemoryapiLab lab = new ProjectpanamaforeignfunctionmemoryapiLab();

        assertThat(lab.getLevel()).isEqualTo(15);
        assertThat(lab.getTopicTitle()).isEqualTo("Project Panama: Foreign Function & Memory API");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
