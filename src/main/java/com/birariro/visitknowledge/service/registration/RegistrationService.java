package com.birariro.visitknowledge.service.registration;

import com.birariro.visitknowledge.adapter.persistence.jpa.member.SlackBot;
import com.birariro.visitknowledge.adapter.persistence.redis.auth.AuthAdapter;
import com.birariro.visitknowledge.adapter.message.event.Events;

import com.birariro.visitknowledge.annotation.AopExecutionTime;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.Email;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.Member;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberRepository;
import com.birariro.visitknowledge.domain.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final MemberRepository memberRepository;
    private final AuthAdapter authAdapter;

    @Async
    @AopExecutionTime
    @Transactional
    public void registration(String email){

        checkEmail(email);
        save(new Email(email));
    }

    @Async
    @AopExecutionTime
    @Transactional
    public void registration(String normalBotToken, String normalBotChannel, String errorBotToken, String errorBotChannel){

        save(new SlackBot(normalBotToken,normalBotChannel,errorBotToken,errorBotChannel));
    }

    @AopExecutionTime
    @Transactional
    public void registrationAuthCode(String authCode){

        UUID id = authAdapter.getAuthCodeMemberId(authCode);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("not exist auth code"));


        log.info(member.getEmail().getValue() + "인증 성공");
        member.active();
    }


    private void checkEmail(String email){
        String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        if(! Pattern.matches(regex, email)){
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    protected void save(Email email){

        Member member = new Member(email);
        memberRepository.save(member);

        String uuid = UUID.randomUUID().toString();
        Events.raise(new NewRegistrationEvent(member, uuid));
    }

    @Transactional
    protected void save(SlackBot slackBot){

        Member member = new Member(slackBot);
        memberRepository.save(member);

        String uuid = UUID.randomUUID().toString();
        Events.raise(new NewRegistrationEvent(member, uuid));
    }

}
