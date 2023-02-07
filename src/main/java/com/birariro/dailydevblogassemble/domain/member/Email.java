package com.birariro.dailydevblogassemble.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @NotNull
    @Column(name = "email", nullable = false)
    private String value;

    public Email(String email) {
        this.value = email;
    }
}
