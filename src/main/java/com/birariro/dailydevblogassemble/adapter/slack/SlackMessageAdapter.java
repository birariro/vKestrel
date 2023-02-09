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
public class SlackMessageAdapter {


    @Value("${slack.error.token}")
    String token;
    @Value("${slack.error.channel}")
    String channel;

    public void sendErrorMessage(String text) throws SlackApiException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("님들아 님들아 큰일났어요 !!!\n");
        stringBuilder.append("배치에서 에러 발생 했어요");
        stringBuilder.append(text);


        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(stringBuilder.toString())
                .build();

        methods.chatPostMessage(request);
    }

    public void sendMessage(String text) throws SlackApiException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(text);

        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(stringBuilder.toString())
                .build();

        methods.chatPostMessage(request);
    }

}
