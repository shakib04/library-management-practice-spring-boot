package com.brac.its.libraryManagement;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.BookRepository;
import com.brac.its.libraryManagement.sevice.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
class LibraryManagementApplicationTests {

    @Autowired
    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;
    static Integer DEFAULT_BOOK_ID = 1;
    static SystemUser user = new SystemUser(1, "shakib", "shakib@mail.com", "1234");

    static Book default_book = new Book(DEFAULT_BOOK_ID, "Book 101 ", "Rakib", "Rock Pubs", 10, user);

    @Test
    public void getBooksTest() {
        Mockito.when(bookService.getAllbook())
                .thenReturn(Stream.of(new Book(101, "Book 101 ", "Rakib", "Rock Pubs", 10, new SystemUser(10001, "shakib@mail.com", "shakib", "1234")),
                        new Book(102, "Book 102", "Rabbi", "Rock Pubs", 5, new SystemUser(10001, "shakib@mail.com", "shakib", "1234"))).collect(Collectors.toList()));

        Assert.assertEquals(2, bookService.getAllbook().size());
    }

    @Test
    public void getBooksByAuthorTest() {

        //
        // Assert.assertEquals();
    }

    @Test
    public void getBookByIdTest() {
        int id = 101;
        Book b1 = new Book(101, "Book 101 ", "Rakib", "Rock Pubs", 10, new SystemUser(10001, "shakib@mail.com", "shakib", "1234"));
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(b1));
    }

    @Test
    public void saveBookTest() {
        Mockito.when(bookService.save(default_book)).thenReturn(withId(default_book));
        DEFAULT_BOOK_ID = default_book.getId();
        Assert.assertEquals(default_book, bookService.save(default_book));
    }

    private Book withId(Book default_book) {
        default_book.setId(11);
        return default_book;
    }

    @Test
    public void updateBookTest(){
        Mockito.when(bookService.update(default_book)).thenReturn(withId(default_book));
    }

    @Test
    public void deleteBookByIdTest() {
        Book book = default_book;
        Mockito.when(bookService.save(default_book)).thenReturn(book);
        Mockito.doNothing().when(bookService).delete(book.getId());
        bookService.delete(book.getId());
    }
}
