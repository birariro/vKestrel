package com.birariro.visitknowledge.adapter;

import com.birariro.visitknowledge.adapter.message.slack.bot.SlackCommonBot;
import com.birariro.visitknowledge.adapter.persistence.jpa.EntityState;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.DocumentRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.Member;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberType;
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
import java.util.stream.Collectors;

@SpringBootTest
public class SlackTextFormatTest {

    @Autowired
    MemberRepository memberRepository;
    @Test
    public void sendMessage() throws SlackApiException, IOException {

        Member members = memberRepository.findAll().stream()
                .filter(item -> item.getEntityState() == EntityState.ACTIVE)
                .filter(item -> item.getType() == MemberType.KNOWLEDGE)
                .findFirst().get();


        String message = "<!here> 두근 두근 :smile:";
        MethodsClient methods = Slack.getInstance().methods(members.getToken());
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(members.getChannel())
                .text(message)
                .build();

        methods.chatPostMessage(request);
    }
}

