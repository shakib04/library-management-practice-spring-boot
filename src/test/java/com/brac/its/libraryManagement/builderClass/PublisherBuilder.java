package com.brac.its.libraryManagement.builderClass;

import com.brac.its.libraryManagement.model.Publisher;

public class PublisherBuilder {
    public static Publisher getPublisher(){
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("fddsfdsfs");
        return publisher;
    }
}
