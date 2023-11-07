package com.birariro.vkestrel.cqrs.port;

import com.birariro.vkestrel.adapter.persistence.member.Member;

public interface MemberCommandRepository {

  Member save(Member member);
  void delete(Member member);
}
