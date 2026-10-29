package io.javahub.level5.asynchronousprogramming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AsyncOrderWorkflowTest {

    @Test
    @DisplayName("CompletableFuture thenCombine should combine price and tax asynchronously")
    void testAsyncCombination() throws Exception {
        AsyncOrderWorkflow workflow = new AsyncOrderWorkflow();
        Double total = workflow.computeTotalWithTax("ORD-101").get();

        assertThat(total).isEqualTo(115.0);
    }
}
