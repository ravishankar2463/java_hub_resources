package com.javahub.level14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RagContextRetrieverTest {

    @Test
    @DisplayName("Verify document ingestion, chunking, and RAG prompt synthesis")
    void testRagPromptSynthesis() {
        RagContextRetriever rag = new RagContextRetriever();
        String corpus = "Virtual threads are lightweight threads introduced in JDK 21. They dramatically reduce the cost of high-throughput web applications. Traditional platform threads map one-to-one with OS threads.";
        rag.ingestDocument("jdk21-guide.txt", corpus, 8);

        var prompt = rag.synthesizePrompt("How do virtual threads work in JDK 21?", 2);

        assertThat(prompt.retrievedChunkCount()).isGreaterThan(0);
        assertThat(prompt.systemPrompt()).contains("Virtual threads");
        assertThat(prompt.systemPrompt()).contains("jdk21-guide.txt");
        assertThat(prompt.userQuery()).isEqualTo("How do virtual threads work in JDK 21?");
    }
}
