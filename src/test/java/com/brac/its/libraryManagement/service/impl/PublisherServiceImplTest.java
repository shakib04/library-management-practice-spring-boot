package com.brac.its.libraryManagement.service.impl;

import com.brac.its.libraryManagement.dto.PublisherBooksDTO;
import com.brac.its.libraryManagement.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class PublisherServiceImplTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPublisher() {
    }

    @Test
    void getAllPublisher() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getPublisherBookList() {
        //publisherService = Mockito.mock(PublisherService.class);
        List<PublisherBooksDTO> publisherBooksDTOList = publisherService.getPublisherBookList();
        assertEquals(publisherBooksDTOList.get(0).getPublisher().getName(), "Kabila");
    }
}