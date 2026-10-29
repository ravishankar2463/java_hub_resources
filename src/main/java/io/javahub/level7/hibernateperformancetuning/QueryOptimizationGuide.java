package io.javahub.level7.hibernateperformancetuning;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mitigates the N+1 select problem by batch-fetching related entities in a single roundtrip.
 */
public class QueryOptimizationGuide {

    public record Parent(long id, String name) {}
    public record Child(long id, long parentId, String value) {}

    public Map<Long, List<Child>> batchFetchChildren(List<Long> parentIds, List<Child> allChildren) {
        return allChildren.stream()
            .filter(c -> parentIds.contains(c.parentId()))
            .collect(Collectors.groupingBy(Child::parentId));
    }
}
