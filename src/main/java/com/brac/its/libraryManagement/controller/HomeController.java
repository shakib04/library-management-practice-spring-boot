package com.brac.its.libraryManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "product/index.html";
    }

    @GetMapping("/login")
    public String login(){
        return "product/index.html";
    }
}
