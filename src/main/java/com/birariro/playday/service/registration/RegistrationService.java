package com.birariro.playday.service.registration;

import com.birariro.playday.adapter.event.registration.RegistrationEventAdapter;
import com.birariro.playday.annotation.AopExecutionTime;
import com.birariro.playday.domain.Email;
import com.birariro.playday.domain.Member;
import com.birariro.playday.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final MemberRepository memberRepository;
    private final RegistrationEventAdapter registrationEventAdapter;

    @AopExecutionTime
    public void registration(String email){

        checkEmail(email);
        save(email);

        //todo 메일 보내는것을 대기 하는 문제 해결 필요
        registrationEventAdapter.newRegistrationPublish(email);
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
