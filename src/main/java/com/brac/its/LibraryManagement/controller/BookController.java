package com.brac.its.LibraryManagement.controller;

import com.brac.its.LibraryManagement.basicOperation.FibonacciSeries;
import com.brac.its.LibraryManagement.model.Book;
import com.brac.its.LibraryManagement.repository.BookRepository;
import com.brac.its.LibraryManagement.sevice.BookService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Book store(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
        log.info("Book Saved " + book);
        return book;
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getById(@PathVariable("id") int id) {
        log.info("Details of book, id = " + id);
        return bookService.getBookById(id);
    }

    @PutMapping("/book")
    public int update(@RequestBody Book book) {
        log.info("Update a book :" + book.getId());
        bookService.saveOrUpdate(book);
        log.info("Book Info Updated" + book);
        return book.getId();
    }

    @DeleteMapping("/book/{id}")
    public String delete(@PathVariable("id") int id) {
        try {
            bookService.delete(id);
        } catch (Exception e) {
            return "failed";
        }
        log.info("Book info updated for " + id);
        return "success";
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
