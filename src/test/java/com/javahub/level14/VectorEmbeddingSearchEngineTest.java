package com.javahub.level14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class VectorEmbeddingSearchEngineTest {

    @Test
    @DisplayName("Verify semantic cosine similarity ranking in vector engine")
    void testCosineSearch() {
        VectorEmbeddingSearchEngine engine = new VectorEmbeddingSearchEngine();

        engine.indexDocument("doc-1", "Java Concurrency & Virtual Threads", new float[]{1.0f, 0.9f, 0.1f});
        engine.indexDocument("doc-2", "Spring Boot Microservices & REST", new float[]{0.2f, 0.1f, 0.9f});
        engine.indexDocument("doc-3", "Thread pool tuning and synchronization", new float[]{0.95f, 0.85f, 0.15f});

        float[] query = new float[]{1.0f, 0.8f, 0.0f}; // strongly aligned with concurrency
        var results = engine.searchNearest(query, 2);

        assertThat(results).hasSize(2);
        assertThat(results.get(0).id()).isEqualTo("doc-1");
        assertThat(results.get(1).id()).isEqualTo("doc-3");
        assertThat(results.get(0).similarityScore()).isGreaterThan(0.95);
    }
}
