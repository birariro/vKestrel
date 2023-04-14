package com.birariro.visitknowledge.controller.api.webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.visitknowledge.service.RegWebHookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WebHookController {

	private final RegWebHookService regWebHookService;

	@PostMapping("/webhook")
	public ResponseEntity reg(@RequestBody WebHookRegRequest webHookRegRequest){

		log.info("slack webhook reg : "+webHookRegRequest.getUrl());
		regWebHookService.registration(webHookRegRequest.getUrl());

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/webhook/{url}")
	public ResponseEntity reg(@PathVariable("url") String url){

		log.info("slack webhook delete : "+url);
		regWebHookService.delete(url);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
