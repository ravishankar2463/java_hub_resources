package io.javahub.level11.contracttestingwithpact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsumerContractVerifierTest {

    @Test
    @DisplayName("Should verify required contract schema fields")
    void testContractVerification() {
        var verifier = new ConsumerContractVerifier();

        var validPayload = Map.<String, Object>of("orderId", "123", "total", 99.0, "status", "CONFIRMED");
        var brokenPayload = Map.<String, Object>of("orderId", "123");

        assertThat(verifier.verifyContractPayload(validPayload)).isTrue();
        assertThat(verifier.verifyContractPayload(brokenPayload)).isFalse();
    }
}
