package com.birariro.playday.config;

import org.springframework.context.ApplicationEventPublisher;

public class Events {
    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(Object event){
        if(publisher == null || event == null) return;
        publisher.publishEvent(event);
    }
}
