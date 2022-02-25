package com.brac.its.libraryManagement.builderClass;

import com.brac.its.libraryManagement.model.Book;

public class BookBuilder {

    public static Book getBook(){
        Book book = new Book();
        book.setId(1);
        book.name("dfdsafd").author("asdsad").copies(2)
                .systemUser(SystemUserBuilder.getSystemUser())
                .publisherDetails(PublisherBuilder.getPublisher());
        return book;
    }
}
