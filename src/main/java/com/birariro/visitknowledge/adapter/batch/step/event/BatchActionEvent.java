package com.birariro.visitknowledge.adapter.batch.step.event;

import com.birariro.visitknowledge.adapter.message.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BatchActionEvent extends BaseEvent {
    private boolean isError;
    private String message;
}


