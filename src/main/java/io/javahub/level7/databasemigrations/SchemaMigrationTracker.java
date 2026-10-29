package io.javahub.level7.databasemigrations;

import java.util.ArrayList;
import java.util.List;

/**
 * Migration state machine orchestrating non-blocking expand and contract migrations.
 */
public class SchemaMigrationTracker {

    public record Migration(int version, String description, String phase) {}

    private final List<Migration> applied = new ArrayList<>();

    public void applyMigration(int version, String desc, String phase) {
        if (!applied.isEmpty() && version <= applied.get(applied.size() - 1).version()) {
            throw new IllegalArgumentException("Migrations must be strictly ascending: " + version);
        }
        applied.add(new Migration(version, desc, phase));
    }

    public List<Migration> getApplied() { return List.copyOf(applied); }
}
