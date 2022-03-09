package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.LibraryManagementApplication;
import com.brac.its.libraryManagement.basicOperation.TestUtil;
import com.brac.its.libraryManagement.controller.helperUtil.MyApplicationDefaultConfig;
import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryManagementApplication.class)
@AutoConfigureMockMvc
@Log
@TestPropertySource(locations = "classpath:application.properties")

 class BookControllerTest {

    @Autowired
    private MockMvc restConfigMockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    MyApplicationDefaultConfig defaultConfig;

    static SystemUser user = new SystemUser(1L, "shakib", "shakib@mail.com", "1234");

    static String RESOURCE_URL = "";
    private static final String  ENTITY_NAME = "book";

    @BeforeEach
    public void init() throws UnknownHostException {
        //http://localhost:8082/book/
        RESOURCE_URL = defaultConfig.getHostAddress() + "/" + ENTITY_NAME  +"/";
    }

    @Test
    public void getBookById() throws Exception {

        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        Book savedBook = bookRepository.saveAndFlush(b1);

        restConfigMockMvc.perform(get(RESOURCE_URL+"/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(b1.getId()))
                .andExpect(jsonPath("$.name").value("Book 101"))
                .andExpect(jsonPath("$.author").value("Rakib"))
                .andExpect(jsonPath("$.publisher").value("Rock Pubs"))
                .andExpect(jsonPath("$.copies").value(10));
    }

    @Test
    public void getBookByNonExistingIdTest() throws Exception {
        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        Book savedBook = bookRepository.saveAndFlush(b1);
        int nonExistingId = savedBook.getId() + 1;

        restConfigMockMvc.perform(get("http://localhost:8080/book/{id}", nonExistingId))
                .andExpect(status().isNotFound());
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
    @Transactional
    public void createBooksTest() throws Exception{
       String defult_name = "Book 101";
       String defualt_author = "Rakib";
       String defualt_pubs = "Rakib";
       int default_copies = 10;
       int sizeBeforeCreate = bookRepository.findAll().size();
       Book b1 = new Book(null, "Book 101", defualt_author, defualt_pubs,10, user);
       Book b = bookRepository.save(b1);
       restConfigMockMvc.perform(post("http://localhost:8080/book")
               .contentType(MediaType.APPLICATION_JSON)
               .content(TestUtil.convertObjectToJsonBytes(b)))
               .andExpect(status().isCreated());
       List<Book> bookList = bookRepository.findAll();
       Assert.assertEquals(bookList.size(), sizeBeforeCreate + 1);
       assertThat(bookList).hasSize(sizeBeforeCreate + 1);
       Book testBook = bookList.get(bookList.size() -1);
       assertThat(testBook.getName()).isEqualTo(defult_name);
       assertThat(testBook.getAuthor()).isEqualTo(defualt_author);
       assertThat(testBook.getCopies()).isEqualTo(default_copies);
    }

    @Test
    @Transactional
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
        updatedBook.setCopies(updatedCopies);

        restConfigMockMvc.perform(put("http://localhost:8080/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(updatedBook)))
                .andExpect(status().isOk());
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(tableSizeBeforeUpdate);
        Book testBook = bookList.get(bookList.size() - 1);
        assertThat(testBook.getName()).isEqualTo(updatedName);
        assertThat(testBook.getAuthor()).isEqualTo(updatedAuthor);
        assertThat(testBook.getCopies()).isEqualTo(updatedCopies);
    }

    @Test
    public void updateWithExistingId() throws Exception{
        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        bookRepository.saveAndFlush(b1);

        Book updatedBook = bookRepository.findById(b1.getId()).get();
        updatedBook.setId(updatedBook.getId() + 1);
        restConfigMockMvc.perform(put("http://localhost:8080/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(updatedBook)))
                .andExpect(status().isNotFound());
    }

     @Test
    public void updateWithNullValue() throws Exception{
        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        bookRepository.saveAndFlush(b1);
        int tableSizeBeforeUpdate = bookRepository.findAll().size();

        Book updatedBook = bookRepository.findById(b1.getId()).get();

        updatedBook.setName(null);
        restConfigMockMvc.perform(put("http://localhost:8080/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(updatedBook)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
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
    @Transactional
    public void deleteBookNonExistingId() throws Exception{
        Book b1 = new Book(null, "Book 101", "Rakib", "Rock Pubs",10, user);
        bookRepository.saveAndFlush(b1);
        int databaseSizeBeforeDelete = bookRepository.findAll().size();

        restConfigMockMvc.perform(delete("http://localhost:8080/book/{id}", b1.getId() + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + "8080" + uri;
    }
}