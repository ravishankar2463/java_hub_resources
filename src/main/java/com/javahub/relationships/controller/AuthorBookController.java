package com.javahub.relationships.controller;
import com.javahub.relationships.entity.Author;
import com.javahub.relationships.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/relationships")
public class AuthorBookController {
    private final EntityManager em;
    public AuthorBookController(EntityManager em) { this.em = em; }
    @PostMapping("/author")
    @Transactional
    public ResponseEntity<Map<String, Object>> createAuthorWithBooks() {
        Author author = new Author("Martin Fowler");
        author.addBook(new Book("Refactoring"));
        author.addBook(new Book("Patterns of Enterprise Application Architecture"));
        em.persist(author);
        return ResponseEntity.ok(Map.of("authorId", author.getId(), "bookCount", author.getBooks().size()));
    }
}
