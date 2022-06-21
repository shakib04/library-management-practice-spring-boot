package com.brac.its.libraryManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    @GetMapping("")
    public String index(){
        return "index.html";
    }

    @GetMapping("/index")
    public String indexV2(){
        return "index.html";
    }
}
