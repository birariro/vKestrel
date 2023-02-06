package com.birariro.playday.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(name = "email")
    private String email;

    public Email(String email) {
        this.email = email;
    }
}
