package com.javahub.vector.service;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class InMemoryVectorStore {
    public record VectorDoc(String id, String content, float[] embedding) {}
    public record ScoredMatch(String id, String content, double similarity) {}
    private final List<VectorDoc> documents = new ArrayList<>();
    public InMemoryVectorStore() {
        documents.add(new VectorDoc("doc-1", "Virtual Threads reduce OS context switching costs", new float[]{0.9f, 0.8f, 0.1f}));
        documents.add(new VectorDoc("doc-2", "Spring Boot auto-configuration simplifies microservices", new float[]{0.1f, 0.2f, 0.95f}));
    }
    public List<ScoredMatch> search(float[] queryVector) {
        return documents.stream()
                .map(d -> new ScoredMatch(d.id(), d.content(), cosine(queryVector, d.embedding())))
                .sorted(Comparator.comparingDouble(ScoredMatch::similarity).reversed())
                .toList();
    }
    private double cosine(float[] a, float[] b) {
        double dot = 0, nA = 0, nB = 0;
        for (int i = 0; i < a.length; i++) { dot += a[i] * b[i]; nA += a[i] * a[i]; nB += b[i] * b[i]; }
        return dot / (Math.sqrt(nA) * Math.sqrt(nB));
    }
}
