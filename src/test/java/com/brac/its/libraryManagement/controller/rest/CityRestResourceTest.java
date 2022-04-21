package com.brac.its.libraryManagement.controller.rest;

import com.brac.its.libraryManagement.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CityRestResource.class)
class CityRestResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CityRepository cityRepository;

    @Test
    void getCities() throws Exception {

        Mockito.when(cityRepository.findAll()).thenReturn(Collections.emptyList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/city").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());

        Mockito.verify(cityRepository.findAll());
    }

    @Test
    void getCitiesWithoutMocking(){
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/city").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().)
    }
}