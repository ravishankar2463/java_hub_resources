package com.javahub.level14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class StructuredOutputRecordParserTest {

    @Test
    @DisplayName("Verify parsing raw LLM JSON response into Java 21 Record")
    void testStructuredOutputParsing() {
        StructuredOutputRecordParser parser = new StructuredOutputRecordParser();
        String json = "{\"sentiment\": \"POSITIVE\", \"confidenceScore\": 0.98, \"keyKeywords\": [\"fast\", \"reliable\", \"clean\"]}";

        var result = parser.parseSentiment(json);
        assertThat(result.sentiment()).isEqualTo("POSITIVE");
        assertThat(result.confidenceScore()).isEqualTo(0.98);
        assertThat(result.keyKeywords()).containsExactly("fast", "reliable", "clean");
    }

    @Test
    @DisplayName("Verify code review finding structured deserialization")
    void testCodeFindingParsing() {
        StructuredOutputRecordParser parser = new StructuredOutputRecordParser();
        String json = "{\"filePath\": \"UserService.java\", \"lineNumber\": 42, \"severity\": \"HIGH\", \"recommendation\": \"Use Virtual Threads\"}";

        var finding = parser.parseCodeFinding(json);
        assertThat(finding.filePath()).isEqualTo("UserService.java");
        assertThat(finding.lineNumber()).isEqualTo(42);
        assertThat(finding.severity()).isEqualTo("HIGH");
        assertThat(finding.recommendation()).isEqualTo("Use Virtual Threads");
    }
}
