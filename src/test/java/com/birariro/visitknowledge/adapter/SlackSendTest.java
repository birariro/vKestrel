package com.birariro.visitknowledge.adapter;

import com.birariro.visitknowledge.adapter.slack.bot.SlackCommonBot;
import com.birariro.visitknowledge.domain.library.Document;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SlackSendTest {

    @Autowired
    SlackCommonBot slackCommonBot;

    @Test
    public void sendTest() {

        Document document = new Document("title", "test", "test");
        Document document2 = new Document("title2", "test2", "test2");
        try {
            slackCommonBot.sendDocument(List.of(document,document2));
        } catch (SlackApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void sendMessage() throws SlackApiException, IOException {
        String token = "";
        System.out.println("token = " + token);
        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("")
                .text("제이름 바뀌었나요?")
                .build();

        methods.chatPostMessage(request);
    }

}

