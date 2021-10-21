package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.repository.BookRepository;
import com.brac.its.libraryManagement.sevice.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
public class BookController {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public List<Book> allBooks() {
        return bookService.getAllbook();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getById(@PathVariable("id") int id) {
        log.debug("Details of book, id = " + id);
        return bookService.getBookById(id);
    }

    @GetMapping("/book/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> store(@RequestBody Book book) {
        Book book2 = bookService.save(book);
        log.debug("Book Saved " + book2);
        return new ResponseEntity<>(book2, HttpStatus.CREATED);
    }

    @PutMapping("/book")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") int id) {
        bookService.delete(id);
    }
}
