package com.birariro.visitknowledge.adapter.persistence.auth;

import com.birariro.visitknowledge.domain.member.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthCodeSubscribe {

    private final AuthAdapter authAdapter;

    @EventListener(NewRegistrationEvent.class)
    public void event(NewRegistrationEvent event){

        log.info("AuthCodeService event uuid : "+event.getAuthCode());
        authAdapter.saveAuthCode(event.getEmail(), event.getAuthCode());
    }
}
