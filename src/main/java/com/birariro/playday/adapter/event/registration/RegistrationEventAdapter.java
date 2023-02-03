package com.birariro.playday.adapter.event.registration;

import com.birariro.playday.config.event.Events;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventAdapter {

    public void newRegistrationPublish(String email){

        Events.raise(new NewRegistrationEvent(email));
    }
}
