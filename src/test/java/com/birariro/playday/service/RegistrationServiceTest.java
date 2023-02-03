package com.birariro.playday.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    RegistrationService registrationService;

    @Test
    @DisplayName("[성공] 이메일 등록 성공")
    public void emailReg(){


        String email = "test@email.com";
        registrationService.registration(email);
    }

    @Test
    @DisplayName("[성공] 이메일 등록시 형식이 안맞으면 예외")
    public void notEmailCharReg(){

        String email = "testemailcom";
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            registrationService.registration(email);
        });
    }
}