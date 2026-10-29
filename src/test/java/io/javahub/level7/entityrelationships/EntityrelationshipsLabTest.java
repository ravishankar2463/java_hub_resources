package io.javahub.level7.entityrelationships;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Entity Relationships.
 */
public class EntityrelationshipsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        EntityrelationshipsLab lab = new EntityrelationshipsLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Entity Relationships");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
