package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.repository.BookRepository;
import com.brac.its.libraryManagement.sevice.BookServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("book/mvc")
public class BookMvcController {

    @Autowired
    private BookServiceV2 bookServiceV2;

    @RequestMapping({"bb", "ss", "ee"})
    @ResponseBody
    public String test(Model model){
        model.addAttribute("tt", bookServiceV2.getAllBooks());
        return "index";
    }
}
