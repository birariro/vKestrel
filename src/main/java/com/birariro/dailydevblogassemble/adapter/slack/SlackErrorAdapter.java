package com.birariro.dailydevblogassemble.adapter.slack;

import com.birariro.dailydevblogassemble.domain.library.Document;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackErrorAdapter {


    @Value("${slack.guard.token}")
    String token;
    @Value("${slack.guard.channel}")
    String channel;

    public void sendMessage(String text) throws SlackApiException, IOException {


        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(text)
                .build();

        methods.chatPostMessage(request);
    }

}
