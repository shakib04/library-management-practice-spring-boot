package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.model.RealCashUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealCashUserRepository extends JpaRepository<RealCashUser, Long> {

}
