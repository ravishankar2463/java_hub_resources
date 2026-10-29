package io.javahub.level11.interservicecommunication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ResilientHttpExecutorTest {

    @Test
    @DisplayName("Should classify 503 as retryable but 404 as non-retryable client error")
    void testHttpRetryClassification() {
        var executor = new ResilientHttpExecutor();

        assertThat(executor.isRetryableHttpCode(503)).isTrue();
        assertThat(executor.isRetryableHttpCode(504)).isTrue();
        assertThat(executor.isRetryableHttpCode(404)).isFalse();
        assertThat(executor.isRetryableHttpCode(400)).isFalse();
    }
}
