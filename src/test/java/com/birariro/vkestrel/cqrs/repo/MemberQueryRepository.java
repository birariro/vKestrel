package com.birariro.vkestrel.cqrs.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.birariro.vkestrel.adapter.persistence.member.Member;

public interface MemberQueryRepository extends Repository<Member, Long> {

  Optional<Member> findById(Long id);
  List<Member> findAll();
}
