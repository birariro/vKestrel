package com.birariro.playday.adapter.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailAdapter {

    @Value("${mail.username}")
    private String from;

    private final TemplateEngine templateEngine;

    private final JavaMailSender sender;


    public void authenticationCodeSend(String email, String authCode){

        log.info(email+" 로 인증 코드 발행");
        String template = getTemplate(authCode);

        MimeMessagePreparator message =
                mimeMessage -> {
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom(from);
                    helper.setTo(email);
                    helper.setSubject("hello");
                    helper.setText(template, true);
                };

        sender.send(message);
    }

    private String getTemplate(String authCode){
        Context context = new Context();
        context.setVariable("result_url","http://localhost:8080/reg/auth/"+authCode);

        String autoCodeForm = templateEngine.process("AuthCodeForm.html", context);
        return autoCodeForm;
    }
}
