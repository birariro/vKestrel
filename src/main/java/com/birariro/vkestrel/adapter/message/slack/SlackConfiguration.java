package com.birariro.vkestrel.adapter.message.slack;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.batch.step.event.DailyDocumentEvent;
import com.birariro.vkestrel.adapter.batch.step.event.LibraryStateSwitchEvent;
import com.birariro.vkestrel.adapter.message.slack.bot.SlackBot;
import com.birariro.vkestrel.adapter.message.slack.bot.SlackWebHook;
import com.birariro.vkestrel.service.member.RegistrationMemberEvent;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackConfiguration {

    private final SlackBot slackBot;
    private final SlackWebHook slackWebHook;

    @EventListener(DailyDocumentEvent.class)
    public void sendDailyDocument(DailyDocumentEvent event) throws SlackApiException, IOException {
        slackWebHook.sendDocument(event.getDocuments());
        slackBot.sendDocumentActiveSuc(SlackConstants.JOB_SUCCESS);
    }

    @EventListener(ActionEvent.class)
    private void actionEvent(ActionEvent event) throws SlackApiException, IOException {

        if(event.isBot()){
            if(event.isError()){
                slackBot.sendErrorMessage(event.getMessage());
                return;
            }
            slackBot.sendCommonMessage(event.getMessage());
            return;
        }

        slackWebHook.sendCommonMessage(event.getMessage());
    }


    @TransactionalEventListener(RegistrationMemberEvent.class)
    public void newRegistrationEvent(RegistrationMemberEvent event){

        slackWebHook.sendCommonMessage(event.getMember(), SlackConstants.WEB_HOOK_NEW_REG_SUCCESS);
    }

    @EventListener(LibraryStateSwitchEvent.class)
    private void libraryStateSwitchEvent(LibraryStateSwitchEvent event) throws SlackApiException, IOException {

        log.info("[SlackConfiguration] LibraryStateSwitchEvent");

        String format = String.format("%s 비활성", event.getName());
        slackBot.sendCommonMessage(format);
    }
}
