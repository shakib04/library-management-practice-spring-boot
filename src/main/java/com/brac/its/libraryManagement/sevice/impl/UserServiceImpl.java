package com.brac.its.libraryManagement.sevice.impl;

import com.brac.its.libraryManagement.dto.UserDTO;
import com.brac.its.libraryManagement.model.User;
import com.brac.its.libraryManagement.repository.UserRepository;
import com.brac.its.libraryManagement.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        return user;
    }
}
