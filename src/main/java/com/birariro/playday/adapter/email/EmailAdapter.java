package com.birariro.playday.adapter.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailAdapter {

    public void authenticationCodeSend(String email){
        log.info(email+" 로 인증 코드 발행");
    }
}
