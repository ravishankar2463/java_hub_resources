package io.javahub.level7.databasemigrations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Database Migrations.
 */
public class DatabasemigrationsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DatabasemigrationsLab lab = new DatabasemigrationsLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Database Migrations");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
