package com.birariro.visitknowledge.adapter.persistence.redis.auth;

import com.birariro.visitknowledge.domain.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthCodeSubscribe {

    private final AuthAdapter authAdapter;

    @TransactionalEventListener(NewRegistrationEvent.class)
    public void event(NewRegistrationEvent event){

        log.info("AuthCodeService event uuid : "+event.getAuthCode());
        authAdapter.saveAuthCode(event.getMember().getId(), event.getAuthCode());
    }
}
