package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.LibraryManagementApplication;
import com.brac.its.libraryManagement.model.Book;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = LibraryManagementApplication.class)
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void findBookByName() {
        Book book = new Book("Test book name 2", "test book publisher", "test author");
        bookRepository.save(book);
        List<Book> bookList = bookRepository.findBookByName("Test book name 2");
        Assert.assertEquals(bookList.size(),  1);
    }


    @Test
    void findFirstByNameLike() {
        Book book = new Book("Test book name 2", "test book publisher", "test author");
        bookRepository.save(book);
        List<Book> bookList = bookRepository.findBookByNameLikeOrNameIsNull("%Test book name%");
        Assert.assertEquals(bookList.size(),  1);
    }
}