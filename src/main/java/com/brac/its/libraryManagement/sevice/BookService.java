package com.brac.its.libraryManagement.sevice;

import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.runtimeExceptions.InvalidDataException;
import com.brac.its.libraryManagement.runtimeExceptions.ResourceNotFoundException;
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

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(book -> books.add(book));
        log.debug(books);
        return books;
    }

    public boolean isOldBook(String isbn){
        if (isbn.startsWith("z")){
            return true;
        }
        return false;
    }

    public boolean isBookStockOut(){
        int result = bookRepository.getBookCopies();
        if (result == 0){
            return true;
        }
        return false;
    }

    public Optional<Book> getBookById(int id) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                return optionalBook;
            } else {
                throw new ResourceNotFoundException("not found.");
            }
        } catch (ResourceNotFoundException exception) {
            //return exception;
            throw exception;
        }
    }

    public Book save(Book book) {
        if (isValidBook(book)) {
            return bookRepository.save(book);
        } else {
            throw new InvalidDataException("Invalid Book Data");
        }
    }

    public boolean isValidBook(Book book) {
        String bookName = book.getName();
        String bookAuthor = book.getAuthor();
        String bookPublisher = book.getPublisher();
        Integer bookCopies = book.getCopies();
        SystemUser createdBy = book.getCreatedBy();
        log.debug("Validating Book Data");
        if (bookName != null &&
                bookAuthor != null &&
                bookPublisher != null &&
                bookCopies != null &&
                createdBy != null &&
                bookName.length() > 3 &&
                bookAuthor.length() > 3 &&
                bookPublisher.length() > 3 &&
                bookCopies > 0
        ) {
            return true;
        }
        return false;
    }

    public void delete(int id) {
        try {
            if (this.getBookById(id) == null) {
                throw new ResourceNotFoundException(String.format("book not found with %d found", id));
            } else {
                log.debug(String.format("delete complete. book id: %d", id));
                bookRepository.deleteById(id);
            }
        } catch (ResourceNotFoundException resourceNOtFoundException) {
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
            if (book.getName() == null) {
                throw new ResourceNotFoundException("");
            } else {
                bookRepository.save(book);
            }
        } catch (ResourceNotFoundException exception) {
            throw exception;
        }
        return book;
    }

    public List<Book> getBooksByName(String name){
       return bookRepository.findBookByName(name);
    }
}
