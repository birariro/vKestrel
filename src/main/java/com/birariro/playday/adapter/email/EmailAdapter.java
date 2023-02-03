package com.birariro.playday.adapter.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailAdapter {

    @Value("${mail.username}")
    private String from;

    private final JavaMailSender sender;
    public void authenticationCodeSend(String email){
        log.info(email+" 로 인증 코드 발행");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("vps32@naver.com");
        message.setFrom(from);
        message.setSubject("hello");
        message.setText("mail world!");
        sender.send(message);
    }
}
