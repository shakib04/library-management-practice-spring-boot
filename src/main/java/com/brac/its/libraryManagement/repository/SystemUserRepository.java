package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {
}
