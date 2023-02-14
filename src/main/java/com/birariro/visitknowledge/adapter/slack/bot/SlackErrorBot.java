package com.birariro.visitknowledge.adapter.slack.bot;

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
public class SlackErrorBot {


    @Value("${slack.bot.error.token}")
    String token;
    @Value("${slack.bot.error.channel}")
    String channel;

    public void sendErrorMessage(String text) throws SlackApiException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("님들아 님들아 큰일났어요 !!!\n");
        stringBuilder.append("에러가 발생 했어요\n");
        stringBuilder.append(text);


        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(stringBuilder.toString())
                .build();

        methods.chatPostMessage(request);
    }



}
