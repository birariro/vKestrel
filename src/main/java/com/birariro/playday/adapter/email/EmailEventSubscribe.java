package com.birariro.playday.adapter.email;

import com.birariro.playday.domain.member.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailEventSubscribe {

    private final EmailAdapter emailAdapter;

    @EventListener(NewRegistrationEvent.class)
    public void event(NewRegistrationEvent event){
        log.info("RegistrationEvent email : "+event.getEmail());
        emailAdapter.authenticationCodeSend(event.getEmail(), event.getAuthCode());
    }
}
