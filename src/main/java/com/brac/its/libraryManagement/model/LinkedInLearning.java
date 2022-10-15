package com.brac.its.libraryManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LinkedInLearning {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
