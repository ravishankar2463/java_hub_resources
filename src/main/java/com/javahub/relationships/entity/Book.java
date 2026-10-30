package com.javahub.relationships.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;
    public Book() {}
    public Book(String title) { this.title = title; }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
