package com.birariro.vkestrel.cqrs.port;

import java.util.List;
import java.util.Optional;

import com.birariro.vkestrel.adapter.persistence.member.Member;

public interface MemberQueryRepository {

  Optional<Member> findById(Long id);
  List<Member> findAll();
}
