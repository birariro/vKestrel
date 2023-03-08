package com.birariro.visitknowledge.adapter.persistence.jpa.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SlackBot {

    private String normalBotToken;
    private String normalBotChannel;
    private String errorBotToken;
    private String errorBotChannel;
}

