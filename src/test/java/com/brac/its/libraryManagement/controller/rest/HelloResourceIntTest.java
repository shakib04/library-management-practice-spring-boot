package com.brac.its.libraryManagement.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(HelloResource.class)
class HelloResourceIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getHelloWorld() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("hello world", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getHelloName() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello?name=shakib");
        mockMvc.perform(requestBuilder).andExpect(content().string("hello shakib"));
    }
}