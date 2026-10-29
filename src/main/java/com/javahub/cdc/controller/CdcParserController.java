package com.javahub.cdc.controller;
import com.javahub.cdc.service.DebeziumEnvelopeParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/cdc")
public class CdcParserController {
    private final DebeziumEnvelopeParser parser;
    public CdcParserController(DebeziumEnvelopeParser parser) { this.parser = parser; }
    @PostMapping("/parse")
    public ResponseEntity<Map<String, String>> parseEvent(@RequestBody DebeziumEnvelopeParser.CdcPayload event) {
        return ResponseEntity.ok(Map.of("summary", parser.describeOperation(event)));
    }
}
