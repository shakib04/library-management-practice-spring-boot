package com.brac.its.libraryManagement.dto;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.Publisher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublisherBooksDTO {
    private Publisher publisher;
    private List<Book> bookList;

    public PublisherBooksDTO(){}
    public PublisherBooksDTO(Publisher publisher, List<Book> bookList){
        this.publisher = publisher;
        this.bookList = bookList;
    }
}
