package io.javahub.level7.fulltextsearchwithelasticsearch;

import java.util.*;

/**
 * Inverted index search engine modeling Elasticsearch text tokenization and posting lists.
 */
public class InvertedIndexEngine {

    private final Map<String, Set<String>> invertedIndex = new HashMap<>();

    public void indexDocument(String docId, String text) {
        String[] tokens = text.toLowerCase().replaceAll("[^a-z0-9\\s]", "").split("\\s+");
        for (String token : tokens) {
            if (!token.isBlank()) {
                invertedIndex.computeIfAbsent(token, k -> new HashSet<>()).add(docId);
            }
        }
    }

    public Set<String> search(String query) {
        String token = query.toLowerCase().trim();
        return invertedIndex.getOrDefault(token, Collections.emptySet());
    }
}
