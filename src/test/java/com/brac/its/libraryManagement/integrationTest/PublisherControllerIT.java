package com.brac.its.libraryManagement.integrationTest;

import com.brac.its.libraryManagement.controller.PublisherController;
import com.brac.its.libraryManagement.model.Publisher;
import com.brac.its.libraryManagement.repository.PublisherRepository;
import com.brac.its.libraryManagement.service.PublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//for get, post, put request

@SpringBootTest
@AutoConfigureMockMvc //all related to mock mvc should be autoconfigured
public class PublisherControllerIT {

    //difference between mockito and mock mvc
    //mockito is used to mock the methods ( normally used in  )
    //mock mvc is used to mock the service class (when integration or api test is done)

    @Autowired
    PublisherController publisherController;

    @Mock
    PublisherRepository publisherRepository;

    @Mock
    PublisherService publisherService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createPublisherTest() throws Exception {

        //while we are creating a book without using a service, not necessary to mock a service
        Publisher publisher = buildPublisher();
        ObjectMapper objectMapper = new ObjectMapper(); //this convert object to stringify json
        String jsonString = objectMapper.writeValueAsString(publisher);
        this.mockMvc.perform(
                post("/api/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());

    }

    @Test
    public void createWithoutSavingDatabase() throws Exception {
        //
        Publisher publisher = buildPublisher();
        Mockito.when(publisherRepository.save(publisher)).thenReturn(publisher);
        String jsonValue = new ObjectMapper().writeValueAsString(publisher);
        this.mockMvc.perform(post("/api/publisher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonValue))
                .andExpect(status().isOk());
    }

    @Test
    public void getPublishersTest() throws Exception{
        Publisher publisher = buildPublisher();
        publisher.setId(1L);
        publisher.setName("no name 2");
        List<Publisher> allPublisher = new ArrayList<>();
        allPublisher.add(publisher);
        //publisherRepository.saveAndFlush(publisher);
        //Mockito.when(publisherService.getAllPublisher()).thenReturn(allPublisher);
        Mockito.when(publisherRepository.findAll()).thenReturn(allPublisher);
        this.mockMvc.perform(
                get("/api/publisher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.[*].id").value(hasItem(publisher.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(publisher.getName())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(publisher.getName())));
    }

    public static Publisher buildPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("No name");
        publisher.setAddress("No Address");
        publisher.setGovtLicence("234324324234");
        publisher.setBook(new HashSet<>());
        return publisher;
    }
}
