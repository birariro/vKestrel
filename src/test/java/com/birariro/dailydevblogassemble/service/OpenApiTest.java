package com.birariro.dailydevblogassemble.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class OpenApiTest {

    @Value("${key.openApi}")
    private String key;
    @Test
    public void getApi(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://openapi.seoul.go.kr:8088/"+key+"/json/culturalEventInfo/1/5/", String.class);
        System.out.println("forEntity = " + forEntity.getBody());
        System.out.println("forEntity = " + forEntity.getStatusCode());
    }

    @Test
    public void key(){
        System.out.println("key = " + key);
    }

}
