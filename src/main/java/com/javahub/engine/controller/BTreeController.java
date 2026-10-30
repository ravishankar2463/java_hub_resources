package com.javahub.engine.controller;
import com.javahub.engine.service.BTreeIndexSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/engine/btree")
public class BTreeController {
    private final BTreeIndexSimulator btree;
    public BTreeController(BTreeIndexSimulator btree) { this.btree = btree; }
    @GetMapping("/seek")
    public ResponseEntity<Map<String, Object>> seek(@RequestParam long key) {
        return ResponseEntity.ok(Map.of("key", key, "pagePointer", btree.seekKey(key), "lookupComplexity", "O(log B N)"));
    }
    @GetMapping("/range")
    public ResponseEntity<Map<Long, String>> range(@RequestParam long min, @RequestParam long max) {
        return ResponseEntity.ok(btree.rangeScan(min, max));
    }
}
