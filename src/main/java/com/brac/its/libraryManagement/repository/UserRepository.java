package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
