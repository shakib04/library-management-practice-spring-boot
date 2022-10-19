package com.brac.its.libraryManagement.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Async
    @JmsListener(destination = "inmemory.queue")
    public void listener(String message){
        System.out.println("Received Message: " + message);
    }
}
