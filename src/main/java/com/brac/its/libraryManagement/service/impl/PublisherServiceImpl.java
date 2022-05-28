package com.brac.its.libraryManagement.service.impl;

import com.brac.its.libraryManagement.model.Publisher;
import com.brac.its.libraryManagement.service.PublisherService;

import java.util.List;
import java.util.Optional;

public class PublisherServiceImpl implements PublisherService {

    @Override
    public Optional<Publisher> getPublisher(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Publisher> getAllPublisher() {
        return null;
    }

    @Override
    public Publisher create(Publisher publisher) {
        return null;
    }

    @Override
    public Publisher update(Publisher publisher) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
