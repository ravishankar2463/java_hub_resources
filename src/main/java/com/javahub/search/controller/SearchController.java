package com.javahub.search.controller;
import com.javahub.search.service.InvertedIndexSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final InvertedIndexSearchService searchService;
    public SearchController(InvertedIndexSearchService searchService) { this.searchService = searchService; }
    @GetMapping
    public ResponseEntity<List<InvertedIndexSearchService.SearchDoc>> query(@RequestParam String q) {
        return ResponseEntity.ok(searchService.search(q));
    }
}
