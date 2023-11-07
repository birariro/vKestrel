package com.birariro.vkestrel.cqrs.port;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.birariro.vkestrel.adapter.persistence.member.Member;
import com.birariro.vkestrel.adapter.persistence.member.MemberRepository;


public class MemberRepositoryImpl implements MemberQueryRepository, MemberCommandRepository{

  @Autowired
  private MemberRepository memberRepository;
  @Override
  public Member save(Member member) {
    return memberRepository.save(member);
  }

  @Override
  public void delete(Member member) {
    memberRepository.delete(member);
  }

  @Override
  public Optional<Member> findById(Long id) {
    return memberRepository.findById(id);
  }

  @Override
  public List<Member> findAll() {
    return memberRepository.findAll();
  }
}
