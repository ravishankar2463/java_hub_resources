package io.javahub.level10.schemaregistryandapacheavro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvroSchemaRegistrySimulatorTest {

    @Test
    @DisplayName("Should prepend 5-byte header and extract schema ID correctly")
    void testSchemaRegistryWireProtocol() {
        var sr = new AvroSchemaRegistrySimulator();
        byte[] payload = new byte[]{0x12, 0x34};

        byte[] wired = sr.prependSchemaHeader(42, payload);
        assertThat(wired).hasSize(7);

        int extractedId = sr.extractSchemaId(wired);
        assertThat(extractedId).isEqualTo(42);
    }
}
