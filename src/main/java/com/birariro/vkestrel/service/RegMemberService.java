package com.birariro.vkestrel.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.member.Member;
import com.birariro.vkestrel.adapter.persistence.member.MemberRepository;
import com.birariro.vkestrel.annotation.AopExecutionTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegMemberService {

    private final MemberRepository memberRepository;

    @Async
    @AopExecutionTime
    @Transactional
    public void registration(String url){

        Member member = new Member(url);
        memberRepository.save(member);
        Events.raise(new RegistrationMemberEvent(member));
    }

    @Transactional
    public void delete(String url){

        Member member = memberRepository.findByUrl(url)
            .orElseThrow(() -> new IllegalStateException());

        member.inActive();
    }
}
