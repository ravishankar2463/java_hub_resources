package com.javahub.engine.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
@Service
public class BTreeIndexSimulator {
    // Simulates an ordered B+Tree leaf page index structure
    private final NavigableMap<Long, String> btreeLeafPages = new ConcurrentSkipListMap<>();
    public BTreeIndexSimulator() {
        btreeLeafPages.put(100L, "Page#12 [Offset: 0x00A0]");
        btreeLeafPages.put(200L, "Page#12 [Offset: 0x01E0]");
        btreeLeafPages.put(300L, "Page#14 [Offset: 0x0040]");
    }
    public String seekKey(long key) { return btreeLeafPages.getOrDefault(key, "NOT_FOUND"); }
    public Map<Long, String> rangeScan(long min, long max) { return btreeLeafPages.subMap(min, true, max, true); }
}
