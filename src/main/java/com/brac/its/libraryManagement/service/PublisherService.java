package com.brac.its.libraryManagement.service;

import com.brac.its.libraryManagement.dto.PublisherBooksDTO;
import com.brac.its.libraryManagement.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    Optional<Publisher> getPublisher(Long id);
    List<Publisher> getAllPublisher();
    Publisher create(Publisher publisher);
    Publisher update(Publisher publisher);
    void delete(Long id);
    List<PublisherBooksDTO> getPublisherBookList();
}
