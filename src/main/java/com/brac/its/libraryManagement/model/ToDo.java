package com.brac.its.libraryManagement.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private String id;
    @NotNull
    private String description;

    private LocalDateTime created;

    private LocalDateTime modified;

    private boolean completed;

    public ToDo(){
        LocalDateTime dateTime = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = dateTime;
        this.modified = dateTime;
    }

    public ToDo(String description){
        this();
        this.description = description;
    }
}
