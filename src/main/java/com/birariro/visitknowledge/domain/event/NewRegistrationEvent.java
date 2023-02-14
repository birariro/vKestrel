package com.birariro.visitknowledge.domain.event;

import com.birariro.visitknowledge.adapter.message.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewRegistrationEvent extends BaseEvent {
    private String email;
    private String authCode;
}
