package io.javahub.level8.springdatajpa;

import java.util.List;
import java.util.function.Predicate;

/**
 * Emulates dynamic Specification and Criteria filtering in Spring Data JPA.
 */
public class DynamicSpecificationFilter {

    public record Product(String id, String category, double price, boolean available) {}

    public List<Product> filter(List<Product> catalog, String category, Double maxPrice, Boolean onlyAvailable) {
        Predicate<Product> predicate = p -> true;

        if (category != null) {
            predicate = predicate.and(p -> p.category().equalsIgnoreCase(category));
        }
        if (maxPrice != null) {
            predicate = predicate.and(p -> p.price() <= maxPrice);
        }
        if (onlyAvailable != null) {
            predicate = predicate.and(p -> p.available() == onlyAvailable);
        }

        return catalog.stream().filter(predicate).toList();
    }
}
