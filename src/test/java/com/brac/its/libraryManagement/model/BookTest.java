package com.brac.its.libraryManagement.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    private void init(){
        book = new Book();
    }

    @Test
    void getId() {
        Integer id = new Integer("33");
        book.setId(id);
        assertEquals(33, book.getId());
    }

    @Test
    void getName() {

    }

    @Test
    void getAuthor() {
    }
}