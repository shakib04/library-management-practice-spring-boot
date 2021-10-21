package com.brac.its.libraryManagement.mockitodemo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ProductServiceTestConfiguration {

    @Bean
    @Primary
    public void bookService(){
        return;
    }
}
