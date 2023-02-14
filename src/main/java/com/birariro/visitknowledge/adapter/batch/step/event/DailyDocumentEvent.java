package com.birariro.visitknowledge.adapter.batch.step.event;

import com.birariro.visitknowledge.domain.event.BaseEvent;
import com.birariro.visitknowledge.domain.library.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DailyDocumentEvent extends BaseEvent {
    private List<Document> documents;
}


