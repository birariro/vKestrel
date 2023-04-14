package com.birariro.vkestrel.domain.event;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;
import com.birariro.vkestrel.adapter.persistence.jpa.WebHook.WebHook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewRegistrationEvent extends BaseEvent {
    private WebHook webHook;
}
