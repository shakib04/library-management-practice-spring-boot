package com.brac.its.libraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String matchingPassword;
}
