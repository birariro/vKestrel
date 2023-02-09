package com.birariro.dailydevblogassemble.adapter.slack;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackAdapter {

    @Value("${slack.token}")
    String token;
    @Value("${slack.channel}")
    String channel;

    public void sendMessage() throws SlackApiException, IOException {
        System.out.println("token = " + token);
        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text("test!")
                .build();

        methods.chatPostMessage(request);
    }
}
