package com.brac.its.libraryManagement.service.impl;

import com.brac.its.libraryManagement.model.Publisher;
import com.brac.its.libraryManagement.repository.PublisherRepository;
import com.brac.its.libraryManagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public Optional<Publisher> getPublisher(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Publisher> getAllPublisher() {
        return publisherRepository.findAll();
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
