package com.brac.its.libraryManagement.sevice;

import com.brac.its.libraryManagement.controller.ResourceNOtFoundException;
import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllbook() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(book -> books.add(book));
        System.out.println("getting data from db:" + books);
        log.debug(books);
        return books;
    }

    public Optional<Book> getBookById(int id) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                return optionalBook;
            } else {
                throw new ResourceNOtFoundException("not found.");
            }
        } catch (ResourceNOtFoundException exception) {
            //return exception;
            throw exception;
        }
    }

    public Book save(Book book) {
       try {
           if(book.getName() != null && !book.getName().isEmpty() && book.getName().length() >  5) {
               bookRepository.save(book);
               return book;
           }else{
               throw new ResourceNOtFoundException("");
           }
       }catch (ResourceNOtFoundException exception){
           return null;
       }

    }

    public void delete(int id) {
        try {
            if (this.getBookById(id) == null){
                throw new ResourceNOtFoundException(String.format("book not found with %d found", id));
            }else{
                log.debug(String.format("delete complete. book id: %d", id));
                bookRepository.deleteById(id);
            }
        }catch (ResourceNOtFoundException resourceNOtFoundException){
            throw resourceNOtFoundException;
        }
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }


    public Book update(Book book) {
        try {
            Optional<Book> optionalBook = this.getBookById(book.getId());
            log.debug(String.format("Update book id : %d", book.getId()));
           if (book.getName() == null){
               throw new ResourceNOtFoundException("");
           }else{
               bookRepository.save(book);
           }
        }
        catch (ResourceNOtFoundException exception){
            throw exception;
        }
        return book;
    }
}
