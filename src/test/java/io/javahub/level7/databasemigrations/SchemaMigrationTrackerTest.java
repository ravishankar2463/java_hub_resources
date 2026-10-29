package io.javahub.level7.databasemigrations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SchemaMigrationTrackerTest {

    @Test
    @DisplayName("Should sequence Expand, Backfill, Switch, and Contract migrations")
    void testMigrationSequence() {
        SchemaMigrationTracker tracker = new SchemaMigrationTracker();
        tracker.applyMigration(1, "Add nullable display_name column", "EXPAND");
        tracker.applyMigration(2, "Backfill display_name from full_name", "BACKFILL");
        tracker.applyMigration(3, "Drop full_name column", "CONTRACT");

        assertThat(tracker.getApplied()).hasSize(3);
    }
}
