package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.Book;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("")
    public List<Book> allBooks() {
        log.debug("REST Request to get book list");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable("id") int id) {
        log.debug("Details of book, id = " + id);
        return bookService.getBookById(id);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @PostMapping("")
    public ResponseEntity<Book> store(@RequestBody Book book) {
        log.debug("REST request to save a book");
        Book book2 = bookService.save(book);
        log.info("Book Saved " + book2);
        return new ResponseEntity<>(book2, HttpStatus.CREATED);
    }

    @PutMapping("")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        bookService.delete(id);
    }

    /**
     *
     * @param name
     * @return list of books by name
     */
    @GetMapping("/find/{name}")
    public ResponseEntity<List<Book>> getBooksByName(@PathVariable String name){
        log.debug("REST request to get Books by name");
         return ResponseEntity.ok().body(bookService.getBooksByName(name));
    }
}
