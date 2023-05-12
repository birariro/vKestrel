package com.birariro.vkestrel.adapter.persistence;

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

    public void inActive(){
        this.entityState = EntityState.INACTIVE;
    }
    public void active(){
        this.setEntityState(EntityState.ACTIVE);
    }

    public boolean isActive(){
        return this.entityState == EntityState.ACTIVE;
    }
}
