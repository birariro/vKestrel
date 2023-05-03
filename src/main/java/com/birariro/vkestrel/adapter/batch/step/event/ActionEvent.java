package com.birariro.vkestrel.adapter.batch.step.event;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ActionEvent extends BaseEvent {
    private boolean isError;
    private String message;
    private boolean isBot;

    public static ActionEvent message(String message){
        return new ActionEvent(false, message,true);
    }
    public static ActionEvent errorMessage(String message){
        return new ActionEvent(true, message,true);
    }

    public ActionEvent(boolean isError, String message, boolean isBot) {
        this.isError = isError;
        this.message = message;
        this.isBot = isBot;
    }

    public ActionEvent(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }
}


