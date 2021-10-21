package com.brac.its.libraryManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNOtFoundException extends RuntimeException {
    public ResourceNOtFoundException(String s) {
        super(s);
    }
}
