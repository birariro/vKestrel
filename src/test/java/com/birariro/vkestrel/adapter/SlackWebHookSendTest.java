package com.birariro.vkestrel.adapter;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class SlackWebHookSendTest {

	@Test
	@DisplayName("web hook test")
	public void webHook(){

		String title = "헬로 웹훅 ";
		String link = "https://slackmojis.com/";
		String message = String.format("<%s|%s>", link, title);

		//String message ="~cancelLine 테스트메시지~";

		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> request = new HashMap<>();
		//request.put("username", "slack메세지 명 :urgent:"); //slack bot name
		request.put("text", message); //전송할 메세지
		//request.put("icon_emoji", ":urgent:"); //slack bot image

		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(request);

		String url = "https://hooks.slack.com/services/~~"; //복사한 Webhook URL 입력

		restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}
}
