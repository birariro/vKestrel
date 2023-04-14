package com.birariro.vkestrel.adapter.batch.step.event;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActionEvent extends BaseEvent {
    private boolean isError;
    private String message;

    public static ActionEvent message(String message){
        return new ActionEvent(false, message);
    }
    public static ActionEvent errorMessage(String message){
        return new ActionEvent(true, message);
    }
}


