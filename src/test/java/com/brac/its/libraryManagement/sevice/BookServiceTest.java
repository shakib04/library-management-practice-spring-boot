package com.brac.its.libraryManagement.sevice;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceV2 bookServiceV2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //bookServiceV2 = new BookServiceV2();
        //bookServiceV2 = new BookServiceV2(bookRepository);
    }

    @Test
    void getAllBooks() {
        Book book = new Book();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> bookList01 = bookServiceV2.getAllBooks();
        assertEquals(bookList01.size(), 1);
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
    }
}