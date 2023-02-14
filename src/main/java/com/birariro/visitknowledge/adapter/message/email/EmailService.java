package com.birariro.visitknowledge.adapter.message.email;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final EmailAuthCode emailAuthCode;
    private final EmailDailyDocuments emailDailyDocuments;


    public void authenticationCodeSend(String email, String authCode){

        emailAuthCode.execute(email,authCode);
    }

    public void toDayDocumentsSend(List<Document> documentList){
        emailDailyDocuments.execute(documentList);
    }

}
