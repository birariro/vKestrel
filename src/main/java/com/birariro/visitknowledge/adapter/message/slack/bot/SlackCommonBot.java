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

        String text = getDocumentsToString(documents);
        sendCommonMessage(text);
    }

    public void sendCommonMessage(String text) throws SlackApiException, IOException {

        List<Member> collect = memberRepository.findAll().stream()
                .filter(item -> item.getEntityState() == EntityState.ACTIVE)
                .filter(item -> item.getType() == MemberType.KNOWLEDGE)
                .collect(Collectors.toList());

        for (Member member : collect) {
            MethodsClient methods = Slack.getInstance().methods(member.getToken());
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(member.getChannel())
                    .text(text)
                    .build();

            methods.chatPostMessage(request);
        }
    }

    private String getDocumentsToString(List<Document> documentList){

        StringBuilder stringBuilder = new StringBuilder();

        for (Document document : documentList) {
            String format = String.format("%s [%s]", document.getTitle(), document.getUrl());
            stringBuilder.append(format);
            if(!document.getAuthor().isBlank()){
                stringBuilder.append(" - "+document.getAuthor());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
