package com.birariro.vkestrel.adapter.persistence.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query("select m from Member m where m.entityState = 'ACTIVE'")
    List<Member> findActiveByAll();
}
