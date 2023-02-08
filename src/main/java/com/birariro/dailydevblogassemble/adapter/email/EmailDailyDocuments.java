package com.birariro.dailydevblogassemble.adapter.email;

import com.birariro.dailydevblogassemble.domain.library.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailDailyDocuments {
    @Value("${mail.username}")
    private String from;

    private final TemplateEngine templateEngine;

    private final JavaMailSender sender;

    public void execute(List<Document> documentList){

        String template = getTemplate(documentList);

        MimeMessagePreparator message =
                mimeMessage -> {
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom(from);
                    helper.setTo("vps32@naver.com");
                    helper.setSubject("today document");
                    helper.setText(template,true);
                };

        sender.send(message);
    }

    private String getTemplate(List<Document> documentList){
        Context context = new Context();
        context.setVariable("documents",documentList);

        String autoCodeForm = templateEngine.process("DailyDocumentsForm.html", context);
        return autoCodeForm;
    }

}
