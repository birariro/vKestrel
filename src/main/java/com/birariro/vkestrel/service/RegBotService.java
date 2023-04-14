package com.birariro.vkestrel.service;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.persistence.jpa.member.*;
import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.annotation.AopExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegBotService {

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
    }

    public void greetings(){
        Events.raise(new ActionEvent(false,"hello"));
    }

}
