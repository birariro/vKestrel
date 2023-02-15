package com.birariro.visitknowledge.adapter.message.slack;

import com.birariro.visitknowledge.adapter.batch.step.event.BatchActionEvent;
import com.birariro.visitknowledge.adapter.batch.step.event.DailyDocumentEvent;
import com.birariro.visitknowledge.adapter.message.slack.bot.SlackCommonBot;
import com.birariro.visitknowledge.adapter.message.slack.bot.SlackErrorBot;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberType;
import com.birariro.visitknowledge.config.exception.ExceptionEvent;
import com.birariro.visitknowledge.domain.event.NewRegistrationEvent;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackConfiguration {

    private final SlackCommonBot slackCommonBot;
    private final SlackErrorBot slackErrorBot;

    @EventListener(DailyDocumentEvent.class)
    public void sendDailyDocument(DailyDocumentEvent event) throws SlackApiException, IOException {
        slackCommonBot.sendDocument(event.getDocuments());
    }

    @EventListener(BatchActionEvent.class)
    private void batchEvent(BatchActionEvent event) throws SlackApiException, IOException {

        if(event.isError()){
            slackErrorBot.sendErrorMessage(event.getMessage());
            return;
        }
        slackCommonBot.sendCommonMessage(event.getMessage());
    }


    @EventListener(ExceptionEvent.class)
    private void errorEvent(ExceptionEvent event) throws SlackApiException, IOException {
        slackErrorBot.sendErrorMessage(event.getMessage());
    }


    @EventListener(NewRegistrationEvent.class)
    public void newRegistrationEvent(NewRegistrationEvent event) throws SlackApiException, IOException {

        if(event.getMember().getType() != MemberType.SLACK) return;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[visit-knowledge] slackBot 연결 요청");
        stringBuilder.append("- 수락하기 : "+ "http://localhost:8080/reg/auth/"+event.getAuthCode());
        slackCommonBot.sendCommonMessage(stringBuilder.toString());
    }
}
