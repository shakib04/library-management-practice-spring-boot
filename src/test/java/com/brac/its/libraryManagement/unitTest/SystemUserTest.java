package com.brac.its.libraryManagement.unitTest;

import com.brac.its.libraryManagement.builderClass.SystemUserBuilder;
import com.brac.its.libraryManagement.model.SystemUser;
import com.brac.its.libraryManagement.repository.BookRepository;
import com.brac.its.libraryManagement.repository.SystemUserRepository;
import com.brac.its.libraryManagement.service.SystemUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemUserTest {


    @InjectMocks
    SystemUserService systemUserService;

    @Spy
    SystemUserRepository systemUserRepository;

    @Autowired
    BookRepository bookRepository;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveASystemUser(){

        //SystemUserService systemUserService = Mockito.mock(SystemUserService.class);
        //SystemUserRepository systemUserRepository = Mockito.mock(SystemUserRepository.class);
        //MockitoAnnotations.initMocks(systemUserRepository);
        Mockito.when(systemUserRepository.save(SystemUserBuilder.getSystemUser())).thenReturn(SystemUserBuilder.getSystemUser());
        Mockito.when(systemUserRepository.findById(SystemUserBuilder.getSystemUser().getId())).thenReturn(Optional.of(SystemUserBuilder.getSystemUser()));
        SystemUser systemUser = systemUserService.save(SystemUserBuilder.getSystemUser());
        SystemUser systemUser1 = systemUserService.findById(systemUser).get();
        Assert.assertEquals(systemUser.getId(), systemUser1.getId());
    }
}
