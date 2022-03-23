package com.brac.its.libraryManagement.unitTest;

import com.brac.its.libraryManagement.builderClass.BookBuilder;
import com.brac.its.libraryManagement.builderClass.PublisherBuilder;
import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.Publisher;
import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.BookRepository;
import com.brac.its.libraryManagement.repository.PublisherRepository;
import com.brac.its.libraryManagement.repository.SystemUserRepository;
import com.brac.its.libraryManagement.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {

    public static final String NEW_BOOK_ISBN = "A123213";
    public static final String OLD_BOOK_ISBN = "Z123213";

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Autowired
    SystemUserRepository systemUserRepository;

    @Autowired
    BookRepository bookRepository2;

    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void checkWithOldBook(){
        boolean result = bookService.isOldBook(OLD_BOOK_ISBN);
        Assert.assertEquals(true, result);
    }

    @Test
    public void checkWithNewBook(){
        boolean result = bookService.isOldBook(NEW_BOOK_ISBN);
        Assert.assertEquals(false, result);
    }

    @Test
    public void bookStockOut(){
        Mockito.when(bookRepository.getBookCopies()).thenReturn(0);
        boolean result = bookService.isBookStockOut();
        Assert.assertEquals(true, result);
    }

    @Test
    public void bookStockIn(){
        Mockito.when(bookRepository.getBookCopies()).thenReturn(10);
        boolean result = bookService.isBookStockOut();
        Assert.assertEquals(false, result);
    }

    @Test
    public void searchBookByName(){
        Book book = BookBuilder.getBook();
        book.name("my name is khan");
        /*SystemUser savedSystemUser = systemUserRepository.saveAndFlush(book.getCreatedBy());
        Publisher savedPublisher = publisherRepository.saveAndFlush(PublisherBuilder.getPublisher());

        book.systemUser(savedSystemUser);
        book.publisherDetails(savedPublisher);

        Book savedBook = bookRepository2.saveAndFlush(book);*/
        List<Book> foundBooks = bookRepository2.searchBookByName("khan");
        Assert.assertEquals(foundBooks.size() > 0, true);
    }

    @Test
    public void sortBooksByName(){
        List<Book> result = bookService.sortBooksByName();
        Assert.assertEquals(result.get(0).getName(), "Akib 75");
    }
}
