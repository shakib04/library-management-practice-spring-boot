package com.brac.its.libraryManagement.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    public String getHelloWorld(){
        return "hello world";
    }

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam(required = false, defaultValue = "world") String name){
        return String.format("hello %s" , name);
    }
}
