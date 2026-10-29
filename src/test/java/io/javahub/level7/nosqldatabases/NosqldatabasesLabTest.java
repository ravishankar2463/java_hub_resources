package io.javahub.level7.nosqldatabases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: NoSQL Databases.
 */
public class NosqldatabasesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        NosqldatabasesLab lab = new NosqldatabasesLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("NoSQL Databases");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
