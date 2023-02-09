package com.birariro.dailydevblogassemble.adapter;

import com.birariro.dailydevblogassemble.adapter.slack.SlackAdapter;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
@SpringBootTest
public class SlackSendTest {

    @Autowired
    SlackAdapter slackAdapter;

    @Test
    public void sendTest() {
        try {
            slackAdapter.sendMessage();
        } catch (SlackApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
