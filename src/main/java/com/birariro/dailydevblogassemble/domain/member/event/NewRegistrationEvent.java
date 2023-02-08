package com.birariro.dailydevblogassemble.domain.member.event;

import com.birariro.dailydevblogassemble.domain.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewRegistrationEvent extends BaseEvent {
    private String email;
    private String authCode;
}
