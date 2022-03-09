package com.brac.its.libraryManagement.builder;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.Publisher;

public class BookBuilder {

    public static Book getBook() {
        Book book = new Book();
        book.setId(1);
        book.name("abcd").publisherDetails(PublisherBuilder.getPublisher()).author("no author");
        return book;
    }
}
