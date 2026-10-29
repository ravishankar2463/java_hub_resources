package io.javahub.level10.changedatacapturewithdebezium;

import java.util.Map;

/**
 * Parses and processes Debezium Change Data Capture (CDC) event envelopes.
 */
public class DebeziumCdcEventParser {

    public record CdcRecord(String op, Map<String, Object> before, Map<String, Object> after, long timestamp) {}

    public String classifyChange(CdcRecord record) {
        return switch (record.op()) {
            case "c" -> "INSERT: Created entity with ID " + record.after().get("id");
            case "u" -> "UPDATE: Modified fields on ID " + record.after().get("id");
            case "d" -> "DELETE: Removed record previously held as ID " + record.before().get("id");
            default -> "UNKNOWN_OP";
        };
    }
}
