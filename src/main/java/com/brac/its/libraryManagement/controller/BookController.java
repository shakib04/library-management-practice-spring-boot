package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.Book;
import com.brac.its.libraryManagement.sevice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ModelAndView books(){
        ModelAndView modelAndView = new ModelAndView("book/index.html");
        List books = bookService.getAllBooks();
        modelAndView.addObject("books",books);
        return modelAndView;
    }
}
