package com.birariro.vkestrel.adapter.persistence.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {

    @Query("select m from Staff m where m.entityState = 'ACTIVE'")
    List<Staff> findActiveByAll();
}
