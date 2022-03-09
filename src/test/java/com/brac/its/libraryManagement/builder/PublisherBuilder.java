package com.brac.its.libraryManagement.builder;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.Publisher;

public class PublisherBuilder {

    public static Publisher getPublisher() {
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("abcd");
        publisher.setAddress("sdsada");
        publisher.setGovtLicence("656546");
        return publisher;
    }
}
