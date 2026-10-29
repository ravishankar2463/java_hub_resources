package com.javahub.cdc.service;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class DebeziumEnvelopeParser {
    public record CdcPayload(String op, Map<String, Object> before, Map<String, Object> after, long timestamp) {}
    public String describeOperation(CdcPayload payload) {
        return switch (payload.op()) {
            case "c" -> "INSERT: Created record with id=" + payload.after().get("id");
            case "u" -> "UPDATE: Changed fields from " + payload.before() + " to " + payload.after();
            case "d" -> "DELETE: Removed record id=" + payload.before().get("id");
            default -> "READ: Snapshot read";
        };
    }
}
