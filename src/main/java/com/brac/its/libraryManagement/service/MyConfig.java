package com.brac.its.libraryManagement.service;

//@Configuration
public class MyConfig {

    //@Bean
    public MyTestService getMyConfigServiceBean(){
        return new MyTestService();
    }
}
