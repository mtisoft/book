package com.example.book.book.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="title",length=64)
    @JsonProperty("title")
    private String title;

    
    @Column(name="author",length=100)
    @JsonProperty("author")
    private String author;

    @Column(name="year",length=11)
    @JsonProperty("year")
    private String year;

    // public Book(){

    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
}
