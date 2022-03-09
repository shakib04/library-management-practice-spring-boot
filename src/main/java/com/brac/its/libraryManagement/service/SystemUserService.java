package com.brac.its.libraryManagement.service;

import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SystemUserService {

    @Autowired
    SystemUserRepository systemUserRepository;


    public SystemUser save(SystemUser systemUser){
        return systemUserRepository.save(systemUser);
    }

    public Optional<SystemUser> findById(SystemUser systemUser){
        return systemUserRepository.findById(systemUser.getId());
    }
}
