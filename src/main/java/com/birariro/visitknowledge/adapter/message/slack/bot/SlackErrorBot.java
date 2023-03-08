package com.birariro.visitknowledge.adapter.message.slack.bot;

import com.birariro.visitknowledge.adapter.persistence.jpa.EntityState;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.Member;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.MemberType;
import com.birariro.visitknowledge.adapter.persistence.jpa.member.SlackBot;
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
public class SlackErrorBot {

    private final MemberRepository memberRepository;

    public void sendErrorMessage(String text) throws SlackApiException, IOException {

        List<Member> collect = memberRepository.findAll().stream()
                .filter(item -> item.getEntityState() == EntityState.ACTIVE)
                .filter(item -> item.getType() == MemberType.ERROR)
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ERROR]\n");
        stringBuilder.append(text);
        for (Member member : collect) {

            MethodsClient methods = Slack.getInstance().methods(member.getToken());
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(member.getChannel())
                    .text(stringBuilder.toString())
                    .build();

            methods.chatPostMessage(request);
        }


    }



}
