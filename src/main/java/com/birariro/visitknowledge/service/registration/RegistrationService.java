package com.birariro.visitknowledge.service.registration;

import com.birariro.visitknowledge.adapter.persistence.jpa.member.*;
import com.birariro.visitknowledge.adapter.message.event.Events;
import com.birariro.visitknowledge.annotation.AopExecutionTime;
import com.birariro.visitknowledge.domain.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final MemberRepository memberRepository;

    @Async
    @AopExecutionTime
    @Transactional
    public void registration(String token, String channel, MemberType type){

        save(token, channel, type);
    }


    @Transactional
    protected void save(String token, String channel, MemberType type){

        Member member = new Member(token, channel, type);
        memberRepository.save(member);

        String uuid = UUID.randomUUID().toString();
        Events.raise(new NewRegistrationEvent(member, uuid));
    }

}
