package com.birariro.vkestrel.adapter.message.slack.bot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.birariro.vkestrel.adapter.message.slack.SlackConstants;
import com.birariro.vkestrel.adapter.persistence.EntityState;
import com.birariro.vkestrel.adapter.persistence.member.Member;
import com.birariro.vkestrel.adapter.persistence.member.MemberRepository;
import com.birariro.vkestrel.adapter.persistence.library.Document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackWebHook {

    private final MemberRepository memberRepository;

    public void sendDocument(List<Document> documents){

        RestTemplate restTemplate = new RestTemplate();

        List<Member> members = getWebHooks();
        log.info("[slack] document count : "+documents.size());

        for (Document document : documents) {
            String text = getDocumentsToString(document);

            for (Member member : members) {
                sendCommonMessage(restTemplate, member, text);
            }

        }
    }
    public void sendCommonMessage(String text){

        List<Member> members = getWebHooks();

        for (Member member : members) {
            sendCommonMessage(member, text);
        }
    }
    public void sendCommonMessage(Member member, String text){

        RestTemplate restTemplate = new RestTemplate();
        sendCommonMessage(restTemplate, member,text);
    }

    private List<Member> getWebHooks(){

        List<Member> members =  memberRepository.findActiveByAll().stream()
            .filter(item -> item.getEntityState() == EntityState.ACTIVE)
            .collect(Collectors.toList());

        log.info("[slack] message webHooks count : "+ members.size());
        return members;
    }
    private void sendCommonMessage(RestTemplate restTemplate, Member member, String text) {

        Map<String, Object> request = new HashMap<>();
        request.put("username", SlackConstants.WEB_HOOK_NAME); //slack bot name
        request.put("text", text); //send message
        request.put("icon_emoji", SlackConstants.WEB_HOOK_EMOJI); //slack bot image

        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(request);

        restTemplate.exchange(member.getUrl(), HttpMethod.POST, entity, String.class);
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
