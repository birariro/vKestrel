package com.birariro.playday.controller;

import com.birariro.playday.service.registration.RegistrationService;
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

    @PostMapping("/reg")
    public ResponseEntity registration(@RequestBody RegRequest regRequest){

        log.info("registration");
        registrationService.registration(regRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/reg/auth/{authCode}")
    public ResponseEntity enableRegistration(@PathVariable("authCode") String authCode){

        log.info("registration auth : "+ authCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
