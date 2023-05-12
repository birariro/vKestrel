package com.birariro.vkestrel.service;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;
import com.birariro.vkestrel.adapter.persistence.member.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationMemberEvent extends BaseEvent {
    private Member member;
}
