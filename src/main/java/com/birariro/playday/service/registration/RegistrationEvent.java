package com.birariro.playday.service.registration;

import com.birariro.playday.config.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationEvent extends BaseEvent {
    private String email;
}
