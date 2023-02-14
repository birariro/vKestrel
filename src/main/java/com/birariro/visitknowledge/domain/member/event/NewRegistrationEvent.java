package com.birariro.visitknowledge.domain.member.event;

import com.birariro.visitknowledge.domain.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewRegistrationEvent extends BaseEvent {
    private String email;
    private String authCode;
}
