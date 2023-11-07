package com.birariro.vkestrel.cqrs.repo;

import org.springframework.data.repository.Repository;

import com.birariro.vkestrel.adapter.persistence.member.Member;

public interface MemberCommandRepository extends Repository<Member, Long> {

  Member save(Member member);
  void delete(Member member);
}
