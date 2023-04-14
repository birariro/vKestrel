package com.birariro.vkestrel.controller.api.bot;

import com.birariro.vkestrel.adapter.persistence.jpa.member.MemberType;
import com.birariro.vkestrel.service.RegBotService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BotController {

    private final RegBotService regBotService;

    @PostMapping("/knowledge/reg")
    public ResponseEntity slackKnowledgeRegistration(@RequestBody BotRequest botRequest){

        log.info("slack knowledge registration");
        regBotService.registration(
                botRequest.getToken(), botRequest.getChannel(), MemberType.KNOWLEDGE
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/error/reg")
    public ResponseEntity slackErrorRegistration(@RequestBody BotRequest botRequest){

        log.info("slack error registration");
        regBotService.registration(
                botRequest.getToken(), botRequest.getChannel(), MemberType.ERROR
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/greetings")
    private void slackKnowledgeGreetings(){
        regBotService.greetings();
    }

}
