package com.birariro.visitknowledge.config.exception;

import com.birariro.visitknowledge.adapter.message.event.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(final RuntimeException e){

        Events.raise(new ExceptionEvent(e.getMessage()));
        return e.getMessage();
    }

}
