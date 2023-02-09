package com.birariro.dailydevblogassemble.adapter.slack;

import com.birariro.dailydevblogassemble.adapter.batch.step.event.DailyDocumentErrorEvent;
import com.birariro.dailydevblogassemble.adapter.batch.step.event.DailyDocumentEvent;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackSubscribe {

    private final SlackAdapter slackAdapter;
    private final SlackErrorAdapter slackErrorAdapter;
    @EventListener(DailyDocumentEvent.class)
    public void sendDailyDocument(DailyDocumentEvent event) throws SlackApiException, IOException {
        slackAdapter.sendMessage(event.getDocuments());
    }

    @EventListener(DailyDocumentErrorEvent.class)
    private void sendError(DailyDocumentErrorEvent event) throws SlackApiException, IOException {
        slackErrorAdapter.sendMessage(event.getError());
    }
}
