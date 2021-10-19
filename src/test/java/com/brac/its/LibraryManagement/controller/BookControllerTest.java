package com.brac.its.LibraryManagement.controller;

import com.brac.its.LibraryManagement.LibraryManagementApplication;
import com.brac.its.LibraryManagement.basicOperation.TestUtil;
import com.brac.its.LibraryManagement.model.Book;
import com.brac.its.LibraryManagement.model.SystemUser;
import com.brac.its.LibraryManagement.repository.BookRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryManagementApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
 class BookControllerTest {

    @Autowired
    private MockMvc restConfigMockMvc;

    @Autowired
    private BookRepository bookRepository;

    static SystemUser user = new SystemUser(1, "shakib", "shakib@mail.com", "1234");

    @Test
    public void getBookByIdTest() throws Exception {

        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        Book savedBook = bookRepository.saveAndFlush(b1);

        restConfigMockMvc.perform(get("http://localhost:8080/book/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(b1.getId()))
                .andExpect(jsonPath("$.name").value("Book 101"))
                .andExpect(jsonPath("$.author").value("Rakib"))
                .andExpect(jsonPath("$.publisher").value("Rock Pubs"))
                .andExpect(jsonPath("$.copies").value(10));
                //.andExpect(jsonPath("$.created_by").value(user));
    }

    @Test
    public void getBooksTest() throws Exception{

        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        Book savedBook = bookRepository.saveAndFlush(b1);

        restConfigMockMvc.perform(get("http://localhost:8080/book"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(b1.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Book 101")))
                .andExpect(jsonPath("$.[*].author").value(hasItem("Rakib")))
                .andExpect(jsonPath("$.[*].publisher").value(hasItem("Rock Pubs")))
                .andExpect(jsonPath("$.[*].copies").value(hasItem(10)));
    }

    @Test
    public void createBooksTest() throws Exception{
       String defualt_name = "Book 101";
       String defualt_author = "Rakib";
       String defualt_pubs = "Rakib";
       int default_copies = 10;
       int sizeBeforeCreate = bookRepository.findAll().size();
       Book b1 = new Book(null, "Book 101", defualt_author, defualt_pubs,10, user);
       Book b = bookRepository.save(b1);
       restConfigMockMvc.perform(post("http://localhost:8080/book")
               .contentType(MediaType.APPLICATION_JSON)
               .content(TestUtil.convertObjectToJsonBytes(b)))
               .andExpect(status().isOk());
       List<Book> bookList = bookRepository.findAll();
       Assert.assertEquals(bookList.size(), sizeBeforeCreate + 1);
       assertThat(bookList).hasSize(sizeBeforeCreate + 1);
       Book testBook = bookList.get(bookList.size() -1);
       assertThat(testBook.getName()).isEqualTo(defualt_name);
       assertThat(testBook.getAuthor()).isEqualTo(defualt_author);
       assertThat(testBook.getPublisher()).isEqualTo(defualt_pubs);
       assertThat(testBook.getCopies()).isEqualTo(default_copies);
    }

    @Test
    public void deleteBooksTest() throws Exception{
        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        bookRepository.saveAndFlush(b1);
        int databaseSizeBeforeDelete = bookRepository.findAll().size();

        restConfigMockMvc.perform(delete("http://localhost:8080/book/{id}", b1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookRepository.findAll();
        Assert.assertEquals(bookList.size(), databaseSizeBeforeDelete - 1);
    }

    @Test
    public void updateBookTest() throws Exception{
        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        bookRepository.saveAndFlush(b1);

        int tableSizeBeforeUpdate = bookRepository.findAll().size();

        Book updatedBook = bookRepository.findById(b1.getId()).get();
        String updatedName = "Book 102";
        String updatedAuthor = "Kalam";
        String updatedPubs = "Legend Pubs";
        int updatedCopies = 5;
        updatedBook.setName(updatedName);
        updatedBook.setAuthor(updatedAuthor);
        updatedBook.setPublisher(updatedPubs);
        updatedBook.setCopies(updatedCopies);
        Book update = bookRepository.save(updatedBook);

        restConfigMockMvc.perform(put("http://localhost:8080/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(update)))
                .andExpect(status().isOk());
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(tableSizeBeforeUpdate);
        Book testBook = bookList.get(bookList.size() - 1);
        assertThat(testBook.getName()).isEqualTo(updatedName);
        assertThat(testBook.getAuthor()).isEqualTo(updatedAuthor);
        assertThat(testBook.getPublisher()).isEqualTo(updatedPubs);
        assertThat(testBook.getCopies()).isEqualTo(updatedCopies);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + "8080" + uri;
    }
}