package io.javahub.level9.graphqlwithnetflixdgs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Emulates Netflix DGS / GraphQL Java DataLoader batch resolving.
 */
public class DgsDataLoaderBatcher {

    public record Author(long id, String name) {}

    public Map<Long, Author> batchLoadAuthors(List<Long> authorIds, List<Author> allAuthors) {
        return allAuthors.stream()
            .filter(a -> authorIds.contains(a.id()))
            .collect(Collectors.toMap(Author::id, a -> a));
    }
}
