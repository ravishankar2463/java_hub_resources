package io.javahub.level10.changedatacapturewithdebezium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class DebeziumCdcEventParserTest {

    @Test
    @DisplayName("Should classify CDC event envelope operations")
    void testCdcParser() {
        var parser = new DebeziumCdcEventParser();

        var insert = new DebeziumCdcEventParser.CdcRecord("c", null, Map.of("id", "U100"), 1000L);
        var update = new DebeziumCdcEventParser.CdcRecord("u", Map.of("id", "U100"), Map.of("id", "U100", "email", "new@javahub.io"), 2000L);

        assertThat(parser.classifyChange(insert)).isEqualTo("INSERT: Created entity with ID U100");
        assertThat(parser.classifyChange(update)).isEqualTo("UPDATE: Modified fields on ID U100");
    }
}
