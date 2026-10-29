package io.javahub.level7.databaseengineinternalsbtreesandmvcc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Database Engine Internals: B-Trees and MVCC.
 */
public class DatabaseengineinternalsbtreesandmvccLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DatabaseengineinternalsbtreesandmvccLab lab = new DatabaseengineinternalsbtreesandmvccLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Database Engine Internals: B-Trees and MVCC");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
