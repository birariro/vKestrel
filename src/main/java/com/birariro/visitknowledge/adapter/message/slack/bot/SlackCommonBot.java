package com.birariro.visitknowledge.adapter.message.slack.bot;

import com.birariro.visitknowledge.adapter.persistence.jpa.EntityState;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.*;
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
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackCommonBot {

    private final MemberRepository memberRepository;

    public void sendDocument(List<Document> documents) throws SlackApiException, IOException {

        log.info("[slack] document count : "+documents.size());
        for (Document document : documents) {
            String text = getDocumentsToString(document);
            sendCommonMessage(text);

        }
    }

    public void sendCommonMessage(String text) throws SlackApiException, IOException {

        List<Member> members = memberRepository.findAll().stream()
                .filter(item -> item.getEntityState() == EntityState.ACTIVE)
                .filter(item -> item.getType() == MemberType.KNOWLEDGE)
                .collect(Collectors.toList());

        log.info("[slack] message member count : "+members.size());
        for (Member member : members) {
            MethodsClient methods = Slack.getInstance().methods(member.getToken());
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(member.getChannel())
                    .text(text)
                    .build();

            methods.chatPostMessage(request);
        }
    }

    private String getDocumentsToString(Document document){

        String title = getSlackTitle(document);
        String link = document.getUrl();

        String message = String.format("<%s|%s>", link, title);
        return message;
    }

    private String getSlackTitle(Document document){

        String title = document.getTitle();
        title = title.replace("<", "&lt;");
        title = title.replace(">","&gt;");

        if(!document.getAuthor().isBlank()){
            title += " - " + document.getAuthor();
        }

        return title;
    }
}
