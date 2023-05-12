package com.birariro.vkestrel.adapter;

import com.birariro.vkestrel.adapter.persistence.EntityState;
import com.birariro.vkestrel.adapter.persistence.staff.Staff;
import com.birariro.vkestrel.adapter.persistence.staff.StaffRepository;
import com.birariro.vkestrel.adapter.persistence.staff.StaffType;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class SlackTextFormatTest {

    @Autowired
    StaffRepository staffRepository;
    @Test
    public void sendMessage() throws SlackApiException, IOException {

        Staff members = staffRepository.findAll().stream()
                .filter(item -> item.getEntityState() == EntityState.ACTIVE)
                .filter(item -> item.getType() == StaffType.KNOWLEDGE)
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

