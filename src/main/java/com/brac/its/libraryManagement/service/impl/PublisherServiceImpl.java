package com.brac.its.libraryManagement.service.impl;

import com.brac.its.libraryManagement.dto.PublisherBooksDTO;
import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.model.Publisher;
import com.brac.its.libraryManagement.repository.PublisherRepository;
import com.brac.its.libraryManagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<PublisherBooksDTO> getPublisherBookList() {
        List<PublisherBooksDTO> publisherBooksDTOList = new ArrayList<>();

        publisherBooksDTOList.add(new PublisherBooksDTO(new Publisher("Rabbil", "sdsad", "323123"), new ArrayList<Book>()));
        publisherBooksDTOList.add(new PublisherBooksDTO(new Publisher("Kabila", "sdsad", "323123"), new ArrayList<Book>()));
        publisherBooksDTOList.add(new PublisherBooksDTO(new Publisher("Mishu", "sdsad", "323123"), new ArrayList<Book>()));

        // documentation for sorting using Comparator
        //// https://www.techiedelight.com/sort-list-of-objects-using-comparator-java/
        Collections.sort(publisherBooksDTOList, new Comparator<PublisherBooksDTO>() {
            @Override
            public int compare(PublisherBooksDTO o1, PublisherBooksDTO o2) {
                return o1.getPublisher().getName().compareTo(o2.getPublisher().getName());
            }
        });

        //publisherBooksDTOList.forEach(x-> System.out.println(x));
        // Collections.sort(publisherBooksDTOList, (o, b) -> o.getPublisher().getName().compareTo(b.getPublisher().getName()));
        // can be converted with Comparator.comparing
        Collections.sort(publisherBooksDTOList, Comparator.comparing(o -> o.getPublisher().getName()));

        return publisherBooksDTOList;
    }
}
