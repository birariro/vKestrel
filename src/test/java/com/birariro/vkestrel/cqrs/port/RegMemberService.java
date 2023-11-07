package com.birariro.vkestrel.cqrs.port;

import org.springframework.beans.factory.annotation.Autowired;


public class RegMemberService {

    @Autowired
    private MemberCommandRepository memberCommandRepository;

    public void test(){
        memberCommandRepository.save(null);
    }

}
