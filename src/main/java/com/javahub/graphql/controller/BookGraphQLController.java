package com.javahub.graphql.controller;

import com.javahub.graphql.model.Author;
import com.javahub.graphql.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class BookGraphQLController {

    private final Map<String, Book> books = Map.of(
        "book-1", new Book("book-1", "Effective Java", 416, "author-1"),
        "book-2", new Book("book-2", "Java Concurrency in Practice", 384, "author-2")
    );

    private final Map<String, Author> authors = Map.of(
        "author-1", new Author("author-1", "Joshua Bloch", "American"),
        "author-2", new Author("author-2", "Brian Goetz", "American")
    );

    @QueryMapping
    public Book bookById(@Argument String id) {
        return books.get(id);
    }

    @QueryMapping
    public List<Book> allBooks() {
        return List.copyOf(books.values());
    }

    @SchemaMapping
    public Author author(Book book) {
        return authors.get(book.authorId());
    }
}
