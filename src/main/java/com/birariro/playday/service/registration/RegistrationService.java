package com.birariro.playday.service.registration;

import com.birariro.playday.config.event.Events;

import com.birariro.playday.annotation.AopExecutionTime;
import com.birariro.playday.domain.member.Email;
import com.birariro.playday.domain.member.Member;
import com.birariro.playday.domain.member.MemberRepository;
import com.birariro.playday.domain.member.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final MemberRepository memberRepository;

    private final RedisTemplate<String, Integer> redisTemplate;

    @AopExecutionTime
    @Transactional
    public void registration(String email){

        checkEmail(email);
        save(email);

        //todo 메일 보내는것을 대기 하는 문제 해결 필요
        String uuid = UUID.randomUUID().toString();
        Events.raise(new NewRegistrationEvent(email, uuid));
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
