package com.brac.its.libraryManagement.sevice;

import com.brac.its.libraryManagement.dto.UserDTO;
import com.brac.its.libraryManagement.model.User;

public interface UserService {

    User registerUser(UserDTO userDTO);
}
