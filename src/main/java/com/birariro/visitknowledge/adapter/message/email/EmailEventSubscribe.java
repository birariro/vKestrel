package com.birariro.visitknowledge.adapter.message.email;

import com.birariro.visitknowledge.adapter.batch.step.event.DailyDocumentEvent;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberType;
import com.birariro.visitknowledge.domain.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailEventSubscribe {

    private final EmailService emailService;

    @EventListener(NewRegistrationEvent.class)
    public void event(NewRegistrationEvent event){

        if(event.getMember().getType() != MemberType.EMAIL) return;

        log.info("RegistrationEvent email : "+event.getMember().getEmail());
        emailService.authenticationCodeSend(event.getMember().getEmail().getValue(), event.getAuthCode());
    }

    @EventListener(DailyDocumentEvent.class)
    public void sendDailyDocument(DailyDocumentEvent event){
        emailService.toDayDocumentsSend(event.getDocuments());
    }
}
