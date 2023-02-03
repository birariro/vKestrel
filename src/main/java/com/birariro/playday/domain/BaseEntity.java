package com.birariro.playday.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name = "create_at",updatable = false)
    private LocalDateTime createAt;

    @Column(name = "state")
    @Convert(converter = StateConverter.class)
    private State state;

    public BaseEntity setState(State state) {
        this.state = state;
        return this;
    }
}
