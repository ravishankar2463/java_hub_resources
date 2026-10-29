package com.javahub.level14;

import java.util.ArrayList;
import java.util.List;

/**
 * Enterprise AI: Retrieval-Augmented Generation (RAG) Engine.
 * Demonstrates document chunking, semantic retrieval injection, and system prompt synthesis.
 */
public class RagContextRetriever {

    public record DocumentChunk(String sourceId, int chunkIndex, String text) {}

    public record AugmentedPrompt(String systemPrompt, String userQuery, int retrievedChunkCount) {}

    private final List<DocumentChunk> knowledgeBase = new ArrayList<>();

    public synchronized void ingestDocument(String sourceId, String fullText, int chunkSize) {
        String[] words = fullText.split("\\s+");
        int index = 0;
        for (int i = 0; i < words.length; i += chunkSize) {
            int end = Math.min(i + chunkSize, words.length);
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < end; j++) {
                sb.append(words[j]).append(" ");
            }
            knowledgeBase.add(new DocumentChunk(sourceId, index++, sb.toString().trim()));
        }
    }

    public synchronized AugmentedPrompt synthesizePrompt(String query, int maxRelevantChunks) {
        List<DocumentChunk> matches = knowledgeBase.stream()
                .filter(chunk -> matchesKeywords(chunk.text(), query))
                .limit(maxRelevantChunks)
                .toList();

        StringBuilder contextBuilder = new StringBuilder("KNOWLEDGE BASE CONTEXT:\n");
        for (DocumentChunk chunk : matches) {
            contextBuilder.append("[").append(chunk.sourceId()).append("#").append(chunk.chunkIndex()).append("] ")
                    .append(chunk.text()).append("\n\n");
        }
        contextBuilder.append("INSTRUCTION: Answer the query truthfully based ONLY on the context above.");

        return new AugmentedPrompt(contextBuilder.toString(), query, matches.size());
    }

    private boolean matchesKeywords(String text, String query) {
        String[] keywords = query.toLowerCase().split("\\s+");
        String lowerText = text.toLowerCase();
        for (String kw : keywords) {
            if (kw.length() > 3 && lowerText.contains(kw)) return true;
        }
        return false;
    }
}
