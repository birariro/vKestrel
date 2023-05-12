package com.birariro.vkestrel.service;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;
import com.birariro.vkestrel.adapter.persistence.WebHook.WebHook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationWebHookEvent extends BaseEvent {
    private WebHook webHook;
}
