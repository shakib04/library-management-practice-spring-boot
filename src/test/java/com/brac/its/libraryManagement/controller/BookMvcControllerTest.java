package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.sevice.BookServiceV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookMvcControllerTest {

    @Mock
    private BookServiceV2 bookServiceV2;
    @InjectMocks
    private BookMvcController bookController;
    @Mock
    private Model model;
    private List<Book> bookList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // given
        bookList = new ArrayList<>();
        Book book = new Book();
        bookList.add(book);
        Mockito.when(bookServiceV2.getAllBooks()).thenReturn(bookList);
    }

    @Test
    void test1() {
        ArgumentCaptor<List<Book>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String result = bookController.test(model);

        // then
        assertEquals("index", result);
        Mockito.verify(bookServiceV2, Mockito.times(1)).getAllBooks();
        Mockito.verify(model, Mockito.times(1))
                .addAttribute(Mockito.
                        eq("tt"), argumentCaptor.capture());

        List<Book> outputFromController = argumentCaptor.getValue();
        assertEquals(1, outputFromController.size());
    }
}