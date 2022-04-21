package com.brac.its.libraryManagement.controller.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloResourceTest {

    @Test
    void getHelloWorld() {
        HelloResource helloResource = new HelloResource();
        String result = helloResource.getHelloWorld();
        assertEquals("hello world", result);
    }
}