package com.birariro.dailydevblogassemble.adapter.slack;

import com.birariro.dailydevblogassemble.adapter.batch.step.event.BatchActionEvent;
import com.birariro.dailydevblogassemble.adapter.batch.step.event.DailyDocumentEvent;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackSubscribe {

    private final SlackAdapter slackAdapter;
    private final SlackMessageAdapter slackMessageAdapter;
    @EventListener(DailyDocumentEvent.class)
    public void sendDailyDocument(DailyDocumentEvent event) throws SlackApiException, IOException {
        slackAdapter.sendMessage(event.getDocuments());
    }

    @EventListener(BatchActionEvent.class)
    private void batchEvent(BatchActionEvent event) throws SlackApiException, IOException {

        if(event.isError()){
            slackMessageAdapter.sendErrorMessage(event.getMessage());
            return;
        }
        slackMessageAdapter.sendMessage(event.getMessage());
    }
}
