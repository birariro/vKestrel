package com.birariro.visitknowledge.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name = "create_at",updatable = false)
    private LocalDateTime createAt;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EntityState entityState;

    public BaseEntity setEntityState(EntityState entityState) {
        this.entityState = entityState;
        return this;
    }
}
