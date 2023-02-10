package com.birariro.dailydevblogassemble.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByEmail(Email email);

    @Query("select m from Member m where m.entityState = 'ACTIVE'")
    List<Member> findActiveByAll();
}
