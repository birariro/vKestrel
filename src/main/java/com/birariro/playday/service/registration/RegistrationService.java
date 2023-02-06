package com.birariro.playday.service.registration;

import com.birariro.playday.adapter.auth.AuthAdapter;
import com.birariro.playday.config.event.Events;

import com.birariro.playday.annotation.AopExecutionTime;
import com.birariro.playday.domain.member.Email;
import com.birariro.playday.domain.member.Member;
import com.birariro.playday.domain.member.MemberRepository;
import com.birariro.playday.domain.member.event.NewRegistrationEvent;
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
        save(email);

        String uuid = UUID.randomUUID().toString();
        Events.raise(new NewRegistrationEvent(email, uuid));
    }

    @AopExecutionTime
    @Transactional
    public void registrationAuthCode(String authCode){

        String authCodeEmail = authAdapter.getAuthCodeEmail(authCode);
        Member member = memberRepository.findByEmail(new Email(authCodeEmail))
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
    protected void save(String email){

        Member member = new Member(new Email(email));
        memberRepository.save(member);
    }

}
