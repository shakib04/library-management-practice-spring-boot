package com.brac.its.LibraryManagement;

import com.brac.its.LibraryManagement.model.Book;
import com.brac.its.LibraryManagement.model.SystemUser;
import com.brac.its.LibraryManagement.repository.BookRepository;
import com.brac.its.LibraryManagement.sevice.BookService;
import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
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
	static Integer DEFAULT_BOOK_ID = null;
	static SystemUser user = new SystemUser(1, "shakib", "shakib@mail.com", "1234");

	static Book default_book = new Book(DEFAULT_BOOK_ID, "Book 101 ", "Rakib", "Rock Pubs", 10, user);

	@Test
	public void getBooksTest(){
		Mockito.when(bookRepository.findAll()).thenReturn(Stream.of(new Book(101, "Book 101 ", "Rakib", "Rock Pubs",10, new SystemUser(10001, "shakib@mail.com", "shakib", "1234")), new Book(102, "Book 102", "Rabbi", "Rock Pubs", 5, new SystemUser(10001, "shakib@mail.com", "shakib", "1234"))).collect(Collectors.toList()));
		Assert.assertEquals(2, bookService.getAllbook().size());
	}

	@Test
	public void getBookByIdTest(){
		int id = 101;
		Book b1 = new Book(101, "Book 101 ", "Rakib", "Rock Pubs",10, new SystemUser(10001, "shakib@mail.com", "shakib", "1234"));
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(b1));
	}

	@Test
	public void saveBookTest(){
		Mockito.when(bookRepository.save(default_book)).thenReturn(withId(default_book));
		DEFAULT_BOOK_ID = default_book.getId();
		Assert.assertEquals(default_book, bookService.save(default_book));
	}

	private Book withId(Book default_book) {
		default_book.setId(11);
		return default_book;
	}

	@Test
	public void deleteBookByIdTest(){
		Optional<Book> b = bookRepository.findById(default_book.getId());
		//Mockito.when(bookService.delete(default_book.getId())).thenReturn(DEFAULT_BOOK_ID);
		Optional<Book> optionalBook = bookRepository.findById(DEFAULT_BOOK_ID);
		Book book1 = null;
		if (optionalBook.isPresent()){
			book1 = optionalBook.get();
		}
		Assertions.assertThat(book1).isNull();
	}


}
