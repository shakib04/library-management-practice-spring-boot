package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @GetMapping("/login")
    public String login(){
        return "auth/login.html";
    }

    @GetMapping("/signup")
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView("auth/signup.html");
        modelAndView.addObject("user",new SystemUser());
        return modelAndView;
    }

    @PostMapping("/doSignup")
    public ModelAndView doSignup(final SystemUser user){
        systemUserRepository.save(user);
        return new ModelAndView("redirect:/login");
    }
}
