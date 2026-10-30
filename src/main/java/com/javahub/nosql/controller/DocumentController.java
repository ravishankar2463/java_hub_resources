package com.javahub.nosql.controller;
import com.javahub.nosql.service.DocumentStoreSimulatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/nosql")
public class DocumentController {
    private final DocumentStoreSimulatorService docStore;
    public DocumentController(DocumentStoreSimulatorService docStore) { this.docStore = docStore; }
    @PostMapping("/docs/{id}")
    public ResponseEntity<Map<String, String>> insert(@PathVariable String id, @RequestBody Map<String, Object> doc) {
        docStore.insert(id, doc);
        return ResponseEntity.ok(Map.of("status", "DOCUMENT_STORED", "id", id));
    }
    @GetMapping("/docs/{id}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable String id) {
        var d = docStore.findById(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }
}
