package com.birariro.playday.service;

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

    public void registration(String email){

        checkEmail(email);
        save(email);

        //todo email 발송
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
