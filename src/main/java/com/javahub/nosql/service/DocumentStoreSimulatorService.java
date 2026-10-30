package com.javahub.nosql.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class DocumentStoreSimulatorService {
    private final Map<String, Map<String, Object>> collections = new ConcurrentHashMap<>();
    public void insert(String id, Map<String, Object> document) { collections.put(id, document); }
    public Map<String, Object> findById(String id) { return collections.get(id); }
    public List<Map<String, Object>> findAll() { return new ArrayList<>(collections.values()); }
}
