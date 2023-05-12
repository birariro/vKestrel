package com.birariro.vkestrel.controller.bot;

import com.birariro.vkestrel.adapter.persistence.member.MemberType;
import com.birariro.vkestrel.service.RegBotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "slack", description = "slack 연결")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BotController {

    private final RegBotService regBotService;

    @Operation(summary = "관리 메시지 봇 등록")
    @PostMapping("/knowledge/reg")
    public ResponseEntity slackKnowledgeRegistration(@RequestBody BotRegRequest botRegRequest){

        log.info("slack knowledge registration");
        regBotService.registration(
                botRegRequest.getToken(), botRegRequest.getChannel(), MemberType.KNOWLEDGE
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "관리 에러 봇 등록")
    @PostMapping("/error/reg")
    public ResponseEntity slackErrorRegistration(@RequestBody BotRegRequest botRegRequest){

        log.info("slack error registration");
        regBotService.registration(
                botRegRequest.getToken(), botRegRequest.getChannel(), MemberType.ERROR
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "전체 webhook 메시지")
    @PostMapping("/noti")
    private void slackNoti(@RequestBody NotiRequest notiRequest){
        regBotService.greetings(notiRequest.getMessage());
    }

}
