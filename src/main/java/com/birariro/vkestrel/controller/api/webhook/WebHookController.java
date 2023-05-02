package com.birariro.vkestrel.controller.api.webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.birariro.vkestrel.service.RegWebHookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "slack", description = "slack 연결")
@RestController
@RequiredArgsConstructor
@Slf4j
public class WebHookController {

	private final RegWebHookService regWebHookService;

	@Operation(summary = "slack webhook 등록")
	@PostMapping("/webhook")
	public ResponseEntity reg(@RequestBody WebHookRegRequest webHookRegRequest){

		log.info("slack webhook reg : "+webHookRegRequest.getUrl());
		regWebHookService.registration(webHookRegRequest.getUrl());

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "slack webhook 제거")
	@DeleteMapping("/webhook/{url}")
	public ResponseEntity reg(@PathVariable("url") String url){

		log.info("slack webhook delete : "+url);
		regWebHookService.delete(url);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
