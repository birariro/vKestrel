package com.birariro.playday.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public void publishAuthenticationCode(String email){
        //todo 인증코드 발행
        log.info(email+" 로 인증 코드 발행");
    }
}
