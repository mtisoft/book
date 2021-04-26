package com.example.book.book.repository;

import com.example.book.book.model.Book;

import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Book,Long> {
    
}
