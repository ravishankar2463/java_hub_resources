package com.javahub.graphql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
class GraphqlApplicationTests {

    @Autowired
    private GraphqlTester graphQlTester;

    @Test
    @DisplayName("Query bookById retrieves nested book and author fields")
    void testBookByIdQuery() {
        String document = """
            query {
                bookById(id: "book-1") {
                    id
                    title
                    author {
                        name
                    }
                }
            }
        """;

        graphQlTester.document(document)
                .execute()
                .path("bookById.title").entity(String.class).isEqualTo("Effective Java")
                .path("bookById.author.name").entity(String.class).isEqualTo("Joshua Bloch");
    }
}
