package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.dto.UserDTO;
import com.brac.its.libraryManagement.model.User;
import com.brac.its.libraryManagement.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return "Success";
    }

}
