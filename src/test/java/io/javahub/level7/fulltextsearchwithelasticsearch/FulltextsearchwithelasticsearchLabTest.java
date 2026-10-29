package io.javahub.level7.fulltextsearchwithelasticsearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Full-Text Search with Elasticsearch.
 */
public class FulltextsearchwithelasticsearchLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        FulltextsearchwithelasticsearchLab lab = new FulltextsearchwithelasticsearchLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Full-Text Search with Elasticsearch");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
