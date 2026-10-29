package com.javahub.avro.service;
import org.apache.avro.Schema;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class AvroSchemaValidatorService {
    private final Map<String, Integer> subjectVersions = new ConcurrentHashMap<>();
    public boolean validateAndRegister(String subject, String schemaJson) {
        try {
            Schema.Parser parser = new Schema.Parser();
            Schema schema = parser.parse(schemaJson);
            subjectVersions.merge(subject, 1, Integer::sum);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public int getVersion(String subject) { return subjectVersions.getOrDefault(subject, 0); }
}
