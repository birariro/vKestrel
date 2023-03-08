package com.birariro.visitknowledge.controller;

import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberType;
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


    @PostMapping("/knowledge/reg")
    public ResponseEntity slackKnowledgeRegistration(@RequestBody SlackRegRequest slackRegRequest){

        log.info("slack knowledge registration");
        registrationService.registration(
                slackRegRequest.getToken(), slackRegRequest.getChannel(), MemberType.KNOWLEDGE
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/error/reg")
    public ResponseEntity slackErrorRegistration(@RequestBody SlackRegRequest slackRegRequest){

        log.info("slack error registration");
        registrationService.registration(
                slackRegRequest.getToken(), slackRegRequest.getChannel(), MemberType.ERROR
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/reg/auth/{authCode}")
    public ResponseEntity enableRegistration(@PathVariable("authCode") String authCode){

        log.info("registration auth : "+ authCode);
        registrationService.registrationAuthCode(authCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
