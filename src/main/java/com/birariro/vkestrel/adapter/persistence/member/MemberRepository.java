package com.birariro.vkestrel.adapter.persistence.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

	@Query("select u from Member u where u.entityState = 'ACTIVE'")
	List<Member> findActiveByAll();

	boolean existsByUrl(String url);
	Optional<Member> findByUrl(String url);
}
