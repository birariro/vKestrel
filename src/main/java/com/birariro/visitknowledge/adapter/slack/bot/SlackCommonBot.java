package com.birariro.visitknowledge.adapter.slack.bot;

import com.birariro.visitknowledge.domain.library.Document;
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
public class SlackCommonBot {


    @Value("${slack.bot.common.token}")
    String token;
    @Value("${slack.bot.common.channel}")
    String channel;

    public void sendDocument(List<Document> documents) throws SlackApiException, IOException {

        String text = getString(documents);
        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(text)
                .build();

        methods.chatPostMessage(request);
    }
    public void sendCommonMessage(String text) throws SlackApiException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(text);

        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(stringBuilder.toString())
                .build();

        methods.chatPostMessage(request);
    }
    private String getString(List<Document> documentList){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[오늘의 뉴스] \n");
        for (Document document : documentList) {
            String format = String.format("%s [%s]", document.getTitle(), document.getUrl());
            stringBuilder.append(format);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
