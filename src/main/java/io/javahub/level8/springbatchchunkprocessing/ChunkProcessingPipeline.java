package io.javahub.level8.springbatchchunkprocessing;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates Spring Batch chunk-oriented transaction processing.
 */
public class ChunkProcessingPipeline {

    public List<List<String>> processInChunks(List<Integer> rawItems, int chunkSize) {
        List<List<String>> committedBatches = new ArrayList<>();
        List<String> currentChunk = new ArrayList<>();

        for (Integer item : rawItems) {
            // ItemProcessor: Transform integer to formatted string
            String processed = "REC_" + (item * 10);
            currentChunk.add(processed);

            // ItemWriter: Commit when chunk boundary is met
            if (currentChunk.size() == chunkSize) {
                committedBatches.add(List.copyOf(currentChunk));
                currentChunk.clear();
            }
        }

        // Flush remainder chunk
        if (!currentChunk.isEmpty()) {
            committedBatches.add(List.copyOf(currentChunk));
        }

        return committedBatches;
    }
}
