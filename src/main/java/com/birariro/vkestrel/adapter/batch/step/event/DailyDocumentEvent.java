package com.birariro.vkestrel.adapter.batch.step.event;

import com.birariro.vkestrel.adapter.message.event.BaseEvent;
import com.birariro.vkestrel.adapter.persistence.library.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DailyDocumentEvent extends BaseEvent {
    private List<Document> documents;
}


