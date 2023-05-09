package com.birariro.vkestrel.adapter.persistence.jpa.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UUID> {

    boolean existsByName(String name);

    @Query("select l from Library l where l.entityState = 'ACTIVE'")
    List<Library> findActiveByAll();

    Optional<Library> findFirstByName(String name);
}
