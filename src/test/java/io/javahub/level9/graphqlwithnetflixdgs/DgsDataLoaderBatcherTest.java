package io.javahub.level9.graphqlwithnetflixdgs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DgsDataLoaderBatcherTest {

    @Test
    @DisplayName("DataLoader should batch resolve author keys in a single operation")
    void testDataLoader() {
        var batcher = new DgsDataLoaderBatcher();
        var authors = List.of(
            new DgsDataLoaderBatcher.Author(1L, "Joshua Bloch"),
            new DgsDataLoaderBatcher.Author(2L, "Martin Fowler")
        );

        var loaded = batcher.batchLoadAuthors(List.of(1L, 2L), authors);

        assertThat(loaded).hasSize(2);
        assertThat(loaded.get(1L).name()).isEqualTo("Joshua Bloch");
    }
}
