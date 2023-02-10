package com.birariro.dailydevblogassemble.config.exception;

import com.birariro.dailydevblogassemble.domain.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionEvent extends BaseEvent {
    private String message;
}
