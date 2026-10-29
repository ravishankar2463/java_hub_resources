package io.javahub.level7.relationaldatabasefundamentalsadvancedsql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Relational Database Fundamentals & Advanced SQL.
 */
public class RelationaldatabasefundamentalsadvancedsqlLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        RelationaldatabasefundamentalsadvancedsqlLab lab = new RelationaldatabasefundamentalsadvancedsqlLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Relational Database Fundamentals & Advanced SQL");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
