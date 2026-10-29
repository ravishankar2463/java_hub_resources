package io.javahub.level7.nosqldatabases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DocumentStoreSimulatorTest {

    @Test
    @DisplayName("Should store JSON document and reject conflicting version updates")
    void testOptimisticConcurrency() {
        var db = new DocumentStoreSimulator();
        db.insert("doc_1", "{\"name\": \"Alice\"}");

        assertThat(db.update("doc_1", "{\"name\": \"Alice V2\"}", 1L)).isTrue();
        assertThat(db.update("doc_1", "{\"name\": \"Alice Stale\"}", 1L)).isFalse();
    }
}
