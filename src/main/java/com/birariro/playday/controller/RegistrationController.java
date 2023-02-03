package com.birariro.playday.controller;

import com.birariro.playday.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/reg")
    public ResponseEntity registration(@RequestBody RegRequest regRequest){

        log.info("registration");
        registrationService.registration(regRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/reg/auth")
    public ResponseEntity enableRegistration(){

        log.info("registration auth");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
