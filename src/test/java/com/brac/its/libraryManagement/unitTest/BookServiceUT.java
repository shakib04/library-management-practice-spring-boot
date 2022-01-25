package com.brac.its.libraryManagement.unitTest;

import com.brac.its.libraryManagement.LibraryManagementApplication;
import com.brac.its.libraryManagement.repository.BookRepository;
import com.brac.its.libraryManagement.sevice.BookService;
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

@RunWith(MockitoJUnitRunner.class)
public class BookServiceUT {

    public static final String NEW_BOOK_ISBN = "A123213";
    public static final String OLD_BOOK_ISBN = "Z123213";

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

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
}
