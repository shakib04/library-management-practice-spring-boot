package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SystemUserController {

    @Autowired
    SystemUserRepository systemUserRepository;

    @PostMapping("/system-user")
    public SystemUser save(@RequestBody SystemUser systemUser){
        return systemUserRepository.save(systemUser);
    }
}
