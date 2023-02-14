package com.birariro.visitknowledge.adapter.batch.step.event;

import com.birariro.visitknowledge.adapter.message.event.BaseEvent;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DailyDocumentEvent extends BaseEvent {
    private List<Document> documents;
}


