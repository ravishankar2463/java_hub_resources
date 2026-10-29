package io.javahub.level10.schemaregistryandapacheavro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Schema Registry and Apache Avro.
 */
public class SchemaregistryandapacheavroLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SchemaregistryandapacheavroLab lab = new SchemaregistryandapacheavroLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Schema Registry and Apache Avro");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
