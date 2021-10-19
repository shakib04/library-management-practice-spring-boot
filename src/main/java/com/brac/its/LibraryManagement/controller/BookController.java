package com.brac.its.LibraryManagement.controller;

import com.brac.its.LibraryManagement.basicOperation.FibonacciSeries;
import com.brac.its.LibraryManagement.model.Book;
import com.brac.its.LibraryManagement.repository.BookRepository;
import com.brac.its.LibraryManagement.sevice.BookService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@RestController
public class BookController {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public List<Book> allBooks(){
        return bookService.getAllbook();
    }

    @PostMapping("/book")
    public ResponseEntity<Book> store(@RequestBody Book book) {
        Book book2 = bookService.save(book);
        log.info("Book Saved " + book2);
        return new ResponseEntity<>(book2, HttpStatus.CREATED);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Optional<Book>> getById(@PathVariable("id") int id) {
        log.info("Details of book, id = " + id);
        try {
            if(bookService.getBookById(id).isPresent()){
                return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
            }else{
                throw new Exception();
            }

        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/book")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        try {
            Optional<Book> optionalBook = bookService.getBookById(book.getId());
            if (optionalBook.isPresent()){
                log.info("Update a book :" + book.getId());
                bookService.save(book);
                log.info("Book Info Updated" + book);
                return new ResponseEntity<>(book, HttpStatus.OK);
            }else {
                throw new Exception();
            }

        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") int id) {
        try {
            if(bookRepository.existsById(id)) {
                bookService.delete(id);
                log.info("deleted book, id = " + id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }else {
                throw new Exception();
            }
        }
        catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public String hello(String text){
        return String.format("Hello %s", text);
    }


    @GetMapping("/fibo")
    public ArrayList<Integer> nn(){
        FibonacciSeries fibonacciSeries = new FibonacciSeries();
      return fibonacciSeries.getFibonacciOfANumber(20);
    }
}
