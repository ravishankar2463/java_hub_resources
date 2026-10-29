package io.javahub.level5.projectloomvirtualthreads;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: Project Loom (Virtual Threads).
 */
public class ProjectloomvirtualthreadsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ProjectloomvirtualthreadsLab lab = new ProjectloomvirtualthreadsLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("Project Loom (Virtual Threads)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
