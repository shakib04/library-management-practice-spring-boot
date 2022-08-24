package com.brac.its.libraryManagement.sevice;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceV2 {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceV2() {
    }

//    public BookServiceV2(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
