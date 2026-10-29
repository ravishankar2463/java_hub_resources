package com.javahub.level14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Enterprise AI: Vector Database & Semantic Similarity Search Engine.
 * Implements high-performance Cosine Similarity across N-dimensional dense vector embeddings.
 */
public class VectorEmbeddingSearchEngine {

    public record DocumentEmbedding(String id, String content, float[] embedding) {
        public DocumentEmbedding {
            Objects.requireNonNull(id, "ID required");
            Objects.requireNonNull(content, "Content required");
            Objects.requireNonNull(embedding, "Embedding vector required");
        }
    }

    public record SearchResult(String id, String content, double similarityScore) {}

    private final List<DocumentEmbedding> index = new ArrayList<>();

    public synchronized void indexDocument(String id, String content, float[] embedding) {
        index.add(new DocumentEmbedding(id, content, embedding));
    }

    public synchronized List<SearchResult> searchNearest(float[] queryVector, int topK) {
        return index.stream()
                .map(doc -> new SearchResult(doc.id(), doc.content(), cosineSimilarity(queryVector, doc.embedding())))
                .sorted(Comparator.comparingDouble(SearchResult::similarityScore).reversed())
                .limit(topK)
                .toList();
    }

    public static double cosineSimilarity(float[] vecA, float[] vecB) {
        if (vecA.length != vecB.length) {
            throw new IllegalArgumentException("Vector dimension mismatch: " + vecA.length + " vs " + vecB.length);
        }
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vecA.length; i++) {
            dotProduct += vecA[i] * vecB[i];
            normA += vecA[i] * vecA[i];
            normB += vecB[i] * vecB[i];
        }

        if (normA == 0.0 || normB == 0.0) return 0.0;
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
