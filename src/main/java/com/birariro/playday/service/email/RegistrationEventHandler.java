package com.birariro.playday.service.email;

import com.birariro.playday.service.registration.RegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationEventHandler {

    private final EmailService emailService;

    @EventListener(RegistrationEvent.class)
    public void event(RegistrationEvent event){
        log.info("RegistrationEvent email : "+event.getEmail());
        emailService.publishAuthenticationCode(event.getEmail());
    }
}
