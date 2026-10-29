package com.javahub.rest.service;

import com.javahub.rest.dto.CreateProductRequest;
import com.javahub.rest.exception.ResourceNotFoundException;
import com.javahub.rest.model.Product;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {

    private final Map<String, Product> database = new ConcurrentHashMap<>();

    public ProductService() {
        // Seed initial items
        saveDirect(new Product("prod-1", "Mechanical Keyboard", "TECH-KB-01", 129.99, Instant.now()));
        saveDirect(new Product("prod-2", "Ultra-Wide Gaming Monitor", "TECH-MON-4K", 499.99, Instant.now()));
    }

    private void saveDirect(Product product) {
        database.put(product.id(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    public Product findById(String id) {
        Product p = database.get(id);
        if (p == null) {
            throw new ResourceNotFoundException("Product with ID '" + id + "' not found");
        }
        return p;
    }

    public Product create(CreateProductRequest request) {
        String id = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        Product p = new Product(id, request.name(), request.sku(), request.price(), Instant.now());
        database.put(id, p);
        return p;
    }

    public void delete(String id) {
        if (!database.containsKey(id)) {
            throw new ResourceNotFoundException("Product with ID '" + id + "' not found");
        }
        database.remove(id);
    }
}
