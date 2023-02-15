package com.birariro.visitknowledge.controller;

import com.birariro.visitknowledge.service.registration.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/email/reg")
    public ResponseEntity emailRegistration(@RequestBody EmailRegRequest emailRegRequest){

        log.info("email registration");
        registrationService.registration(emailRegRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/email/reg/auth/{authCode}")
    public ResponseEntity emailEnableRegistration(@PathVariable("authCode") String authCode){

        log.info("email registration auth : "+ authCode);
        registrationService.registrationAuthCode(authCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/slack/reg")
    public ResponseEntity slackRegistration(@RequestBody SlackRegRequest slackRegRequest){

        log.info("slack registration");
        registrationService.registration(
                slackRegRequest.getNormalBotToken(), slackRegRequest.getNormalBotChannel(),
                slackRegRequest.getErrorBotToken(), slackRegRequest.getErrorBotChannel()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
