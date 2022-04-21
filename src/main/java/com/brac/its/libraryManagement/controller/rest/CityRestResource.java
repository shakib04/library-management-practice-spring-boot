package com.brac.its.libraryManagement.controller.rest;

import com.brac.its.libraryManagement.model.City;
import com.brac.its.libraryManagement.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityRestResource {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("")
    public List<City> getCities(){
        return cityRepository.findAll();
    }
}
