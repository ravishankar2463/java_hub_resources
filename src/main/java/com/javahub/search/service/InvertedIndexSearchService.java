package com.javahub.search.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class InvertedIndexSearchService {
    public record SearchDoc(String id, String text) {}
    private final Map<String, Set<String>> invertedIndex = new ConcurrentHashMap<>();
    private final Map<String, String> documents = new ConcurrentHashMap<>();
    public InvertedIndexSearchService() {
        indexDoc("doc-1", "Java 21 introduces virtual threads for high concurrency web applications");
        indexDoc("doc-2", "Spring Boot microservices architecture with Kafka streaming and Docker");
    }
    public void indexDoc(String id, String text) {
        documents.put(id, text);
        for (String word : text.toLowerCase().split("\\W+")) {
            if (word.length() > 3) {
                invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(id);
            }
        }
    }
    public List<SearchDoc> search(String query) {
        Set<String> matches = invertedIndex.getOrDefault(query.toLowerCase(), Set.of());
        return matches.stream().map(id -> new SearchDoc(id, documents.get(id))).toList();
    }
}
