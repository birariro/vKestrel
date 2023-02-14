package com.birariro.visitknowledge.config.exception;

import com.birariro.visitknowledge.adapter.message.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionEvent extends BaseEvent {
    private String message;
}
