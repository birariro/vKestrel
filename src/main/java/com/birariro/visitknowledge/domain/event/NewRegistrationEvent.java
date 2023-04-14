package com.birariro.visitknowledge.domain.event;

import com.birariro.visitknowledge.adapter.message.event.BaseEvent;
import com.birariro.visitknowledge.adapter.persistence.jpa.WebHook.WebHook;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewRegistrationEvent extends BaseEvent {
    private WebHook webHook;
}
