package com.example.book.book.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.book.book.model.Book;
import com.example.book.book.repository.BooksRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("book")
public class BookControler {

    BooksRepository booksRepository;

    BookControler(BooksRepository booksRepository){
        this.booksRepository = booksRepository;
    }
    
    @PostMapping("/add")
    ResponseEntity<Book> creat_book(@RequestBody Book book){
        return new ResponseEntity<Book>(booksRepository.save(book),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<Book>> get_all_book(){

        List<Book> books = new ArrayList<>();
        
        Iterable<Book> iterable = booksRepository.findAll();
        iterable.forEach(book ->{
            books.add(book);
        });

        return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
    }

    @GetMapping("/{index}")
    ResponseEntity<Book> get_book_byID(@PathVariable Long index){

        Optional<Book> request = booksRepository.findById(index);

        if (!request.isPresent()){
            return new ResponseEntity<Book>(new Book(),HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<Book>(request.get(),HttpStatus.OK);
        }
    }

    @PutMapping("/update/{index}")
    ResponseEntity<Book> update_book( @RequestBody Book book , @PathVariable Long index){

        Optional<Book> request = booksRepository.findById(index);

        if (!request.isPresent()){
            return new ResponseEntity<Book>(new Book(),HttpStatus.BAD_REQUEST);
        }
        else{
            
            Book bk = request.get();
            bk.setTitle(book.getTitle());
            bk.setAuthor(book.getAuthor());
            bk.setYear(book.getYear());

            booksRepository.save(bk);

            return new ResponseEntity<Book>(bk,HttpStatus.OK);
        }

    }

    @DeleteMapping("/del/{index}")
    ResponseEntity<Book> delete_book(@PathVariable Long index){

        Optional<Book> request = booksRepository.findById(index);

        if (!request.isPresent()){
            return new ResponseEntity<Book>(new Book(),HttpStatus.BAD_REQUEST);
        }
        else{
            Book bk = request.get();
            booksRepository.delete(bk);
            return new ResponseEntity<Book>(bk,HttpStatus.OK);
        }
    }

}
