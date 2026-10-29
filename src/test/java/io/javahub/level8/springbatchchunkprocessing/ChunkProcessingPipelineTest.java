package io.javahub.level8.springbatchchunkprocessing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ChunkProcessingPipelineTest {

    @Test
    @DisplayName("Should commit items in discrete chunks respecting configured chunk size")
    void testChunkProcessing() {
        var pipeline = new ChunkProcessingPipeline();
        var items = List.of(1, 2, 3, 4, 5);

        var batches = pipeline.processInChunks(items, 2);

        assertThat(batches).hasSize(3);
        assertThat(batches.get(0)).containsExactly("REC_10", "REC_20");
        assertThat(batches.get(1)).containsExactly("REC_30", "REC_40");
        assertThat(batches.get(2)).containsExactly("REC_50");
    }
}
