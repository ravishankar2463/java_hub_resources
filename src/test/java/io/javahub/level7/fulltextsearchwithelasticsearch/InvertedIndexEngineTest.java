package io.javahub.level7.fulltextsearchwithelasticsearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvertedIndexEngineTest {

    @Test
    @DisplayName("Inverted index should find document IDs containing matching tokens")
    void testInvertedIndex() {
        var engine = new InvertedIndexEngine();
        engine.indexDocument("doc1", "Elasticsearch provides high performance inverted index search");
        engine.indexDocument("doc2", "Java 21 delivers virtual threads and memory performance");

        assertThat(engine.search("inverted")).containsExactly("doc1");
        assertThat(engine.search("performance")).containsExactlyInAnyOrder("doc1", "doc2");
        assertThat(engine.search("unknown")).isEmpty();
    }
}
