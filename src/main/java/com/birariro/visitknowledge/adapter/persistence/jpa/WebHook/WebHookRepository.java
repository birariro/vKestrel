package com.birariro.visitknowledge.adapter.persistence.jpa.WebHook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebHookRepository extends JpaRepository<WebHook,Long> {

	@Query("select u from WebHook u where u.entityState = 'ACTIVE'")
	List<WebHook> findActiveByAll();

	boolean existsByUrl(String url);
	Optional<WebHook> findByUrl(String url);
}
