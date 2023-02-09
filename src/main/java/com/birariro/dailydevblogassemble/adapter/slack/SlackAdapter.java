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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackAdapter {


    @Value("${slack.token}")
    String token;
    @Value("${slack.channel}")
    String channel;

    public void sendMessage(List<Document> documents) throws SlackApiException, IOException {

        String text = getString(documents);
        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(text)
                .build();

        methods.chatPostMessage(request);
    }
    private String getString(List<Document> documentList){

        StringBuilder stringBuilder = new StringBuilder();

        for (Document document : documentList) {
            String format = String.format("%s [%s]", document.getTitle(), document.getUrl());
            stringBuilder.append(format);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
