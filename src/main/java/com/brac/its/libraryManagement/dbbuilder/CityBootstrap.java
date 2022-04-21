package com.brac.its.libraryManagement.dbbuilder;

import com.brac.its.libraryManagement.model.City;
import com.brac.its.libraryManagement.repository.CityRepository;
import com.brac.its.libraryManagement.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CityBootstrap implements CommandLineRunner {

    @Autowired
    CityRepository cityRepository;

    @Override
    public void run(String... args) throws Exception {
        City city = new City();

        city.setName(StringUtil.getRandomString(7));
        //cityRepository.saveAndFlush(city);
    }
}
