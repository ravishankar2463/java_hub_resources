package com.javahub.vector.controller;
import com.javahub.vector.service.InMemoryVectorStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/vectors")
public class VectorSearchController {
    private final InMemoryVectorStore vectorStore;
    public VectorSearchController(InMemoryVectorStore vectorStore) { this.vectorStore = vectorStore; }
    @GetMapping("/search")
    public ResponseEntity<List<InMemoryVectorStore.ScoredMatch>> searchNearest() {
        float[] concurrencyQuery = new float[]{0.95f, 0.75f, 0.05f};
        return ResponseEntity.ok(vectorStore.search(concurrencyQuery));
    }
}
