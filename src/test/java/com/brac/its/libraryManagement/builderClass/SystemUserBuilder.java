package com.brac.its.libraryManagement.builderClass;

import com.brac.its.libraryManagement.model.SystemUser;

public class SystemUserBuilder {

    public static SystemUser getSystemUser(){
        SystemUser systemUser = new SystemUser();
        systemUser.setId(1L);
        systemUser.setName("System user");
        systemUser.setEmail("sys@mail.com");
        systemUser.setPassword("sdfsd234fsdf34w5rvcxrewtewfwe4r324e324234q34324532r");
        return systemUser;
    }
}
