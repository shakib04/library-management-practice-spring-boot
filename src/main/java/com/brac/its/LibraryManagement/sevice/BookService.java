package com.brac.its.LibraryManagement.sevice;

import com.brac.its.LibraryManagement.model.Book;
import com.brac.its.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllbook(){
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(book -> books.add(book));
        System.out.println("getting data from db:" + books);
        return  books;
    }

    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }

    public Book save(Book book){
        bookRepository.save(book);
        return book;
    }

    public int delete(int id){
        bookRepository.deleteById(id);
        return id;
    }


}
