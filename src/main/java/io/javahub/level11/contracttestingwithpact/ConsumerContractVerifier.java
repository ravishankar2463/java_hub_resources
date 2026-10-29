package io.javahub.level11.contracttestingwithpact;

import java.util.Map;

/**
 * Validates consumer-driven contracts between microservice APIs.
 */
public class ConsumerContractVerifier {

    public boolean verifyContractPayload(Map<String, Object> payload) {
        return payload.containsKey("orderId") &&
               payload.containsKey("total") &&
               payload.containsKey("status");
    }
}
