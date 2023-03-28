package com.birariro.visitknowledge.adapter;

import com.birariro.visitknowledge.adapter.message.slack.bot.SlackCommonBot;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.DocumentRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberRepository;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SlackSendTest {

    @Autowired
    SlackCommonBot slackCommonBot;

    @Autowired
    DocumentRepository documentRepository;


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

    @Test
    @DisplayName("slack message cancelLine 테스트")
    public void testSlackCancelLineMessage() throws SlackApiException, IOException {

        String message ="~cancelLine 테스트메시지~";
        slackCommonBot.sendCommonMessage(message);
    }

    @Test
    @DisplayName("slack message link 테스트")
    public void testSlackLinkMessage() throws SlackApiException, IOException {

        String message ="<https://medium.com/p/d2a52359fe85|WATCHA-CTI-s-Case-Report:-Join My Table-1부 들어가며 - gadgetlip>";
        slackCommonBot.sendCommonMessage(message);
    }


    @Test
    @DisplayName("slack message link 테스트")
    public void testSlackLinkMessage4() throws SlackApiException, IOException {

        Document document = documentRepository.findAll()
                .stream()
                .filter(item -> item.getTitle().contains("WATCHA CTI’s Case Report: <Join My Ta"))
                .findFirst()
                .get();

        System.out.println("document1 = " + document);

        String title = document.getTitle();

        title = title.replace("<", "&lt;");
        title = title.replace(">","&gt;");
        String link = document.getUrl();
        if(!document.getAuthor().isBlank()){
            title += " - " + document.getAuthor();
        }

        String message = String.format("<%s|%s>", link, title);

        System.out.println("message = " + message);

        slackCommonBot.sendCommonMessage(message);
    }
}

