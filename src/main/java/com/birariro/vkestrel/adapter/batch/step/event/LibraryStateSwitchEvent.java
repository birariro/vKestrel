package com.birariro.vkestrel.adapter.batch.step.event;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;

import lombok.Getter;

@Getter
public class LibraryStateSwitchEvent extends BaseEvent {
    private boolean isState;
    private boolean isError;
    private String name;

    public static LibraryStateSwitchEvent inActive(String name){
        return new LibraryStateSwitchEvent(false,false, name);
    }
    public static LibraryStateSwitchEvent active(String name){
        return new LibraryStateSwitchEvent(true,false, name);
    }
    public static LibraryStateSwitchEvent error(String name){
        return new LibraryStateSwitchEvent(true,true, name);
    }

    public LibraryStateSwitchEvent(boolean isState, boolean isError, String name) {
        this.isState = isState;
        this.name = name;
        this.isError = isError;
    }
}


