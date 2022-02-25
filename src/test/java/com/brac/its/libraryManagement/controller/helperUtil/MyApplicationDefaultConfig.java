package com.brac.its.libraryManagement.controller.helperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class MyApplicationDefaultConfig {

    @Autowired
    Environment environment;

    // Port via annotation
    @Value("${server.port}")
    int aPort;

    public String getHostAddress() throws UnknownHostException {
        // Port
        String port = environment.getProperty("server.port");

        // Local address
        InetAddress.getLocalHost().getHostAddress();
        InetAddress.getLocalHost().getHostName();

        // Remote address
        String address = InetAddress.getLoopbackAddress().getHostAddress();
        String hostName = InetAddress.getLoopbackAddress().getHostName();

        return "http://" + hostName + ":" +  port;
    }
}
