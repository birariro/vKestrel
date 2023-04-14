package com.birariro.visitknowledge.controller.api.test;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.visitknowledge.adapter.batch.step.event.DailyDocumentEvent;
import com.birariro.visitknowledge.adapter.message.event.Events;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/document")
public class DocumentController {

	private final DocumentRepository documentRepository;

	@GetMapping
	public void slackDocument(){

		List<Document> top5 = documentRepository.findAll().stream().limit(5).collect(Collectors.toList());
		Events.raise(new DailyDocumentEvent(top5));
	}
}
