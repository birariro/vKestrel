package com.birariro.dailydevblogassemble.adapter.batch.step.event;

import com.birariro.dailydevblogassemble.domain.event.BaseEvent;
import com.birariro.dailydevblogassemble.domain.library.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DailyDocumentEvent extends BaseEvent {
    private List<Document> documents;
}


