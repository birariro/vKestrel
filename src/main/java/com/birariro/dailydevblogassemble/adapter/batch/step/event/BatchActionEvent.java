package com.birariro.dailydevblogassemble.adapter.batch.step.event;

import com.birariro.dailydevblogassemble.domain.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BatchActionEvent extends BaseEvent {
    private boolean isError;
    private String message;
}


