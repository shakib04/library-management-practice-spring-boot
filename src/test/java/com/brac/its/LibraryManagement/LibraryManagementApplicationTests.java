package com.brac.its.LibraryManagement;

import com.brac.its.LibraryManagement.model.Book;
import com.brac.its.LibraryManagement.model.SystemUser;
import com.brac.its.LibraryManagement.repository.BookRepository;
import com.brac.its.LibraryManagement.sevice.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class LibraryManagementApplicationTests {


	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	@Test
	public void getBooksTest(){
		Mockito.when(bookRepository.findAll()).thenReturn(Stream.of(new Book(101, "Book 101 ", "Rakib", "Rock Pubs",10, new SystemUser(10001, "shakib@mail.com", "shakib", "1234")), new Book(102, "Book 102", "Rabbi", "Rock Pubs", 5, new SystemUser(10001, "shakib@mail.com", "shakib", "1234"))).collect(Collectors.toList()));

		Assertions.assertEquals(2, bookService.getAllbook().size());
	}

	@Test
	public void saveBookTest(){
		Book book = new Book(101, "Book 101 ", "Rakib", "Rock Pubs", 10, new SystemUser(10001, "shakib", "shakib@email.com", "1234"));
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		Assert.assertEquals(book, bookService.saveOrUpdate(book));
	}

	@Test
	public void deleteBookByIdTest(){
		Book book = new Book(101, "Book 101 ", "Rakib", "Rock Pubs", 10, new SystemUser(10001, "shakib", "shakib@mail.com", "1234"));
		bookService.delete(book.getId());
		Mockito.verify(bookRepository, Mockito.times(1)).deleteById(book.getId());
	}


	@Test
	public void getBookByIdTest(){
		int id = 101;
		Book b1 = new Book(101, "Book 101 ", "Rakib", "Rock Pubs",10, new SystemUser(10001, "shakib@mail.com", "shakib", "1234"));
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(b1));

	}

	@Test
	void contextLoads() {
	}

}
