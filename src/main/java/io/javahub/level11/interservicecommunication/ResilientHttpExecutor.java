package io.javahub.level11.interservicecommunication;

import java.time.Duration;

/**
 * Resilient HTTP client wrapper demonstrating connection timeouts and retry classification.
 */
public class ResilientHttpExecutor {

    public record RequestConfig(Duration connectTimeout, Duration readTimeout, int maxRetries) {}

    public boolean isRetryableHttpCode(int httpStatusCode) {
        // 502 Bad Gateway, 503 Service Unavailable, 504 Gateway Timeout are retryable
        return httpStatusCode == 502 || httpStatusCode == 503 || httpStatusCode == 504;
    }
}
