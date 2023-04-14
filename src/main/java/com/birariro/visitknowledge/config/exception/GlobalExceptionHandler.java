package com.birariro.visitknowledge.config.exception;

import com.birariro.visitknowledge.adapter.batch.step.event.ActionEvent;
import com.birariro.visitknowledge.adapter.message.event.Events;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(final RuntimeException e){

        Events.raise(new ActionEvent(true,e.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
