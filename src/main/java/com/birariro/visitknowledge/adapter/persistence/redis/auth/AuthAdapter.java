package com.birariro.visitknowledge.adapter.persistence.redis.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthAdapter {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveAuthCode(String email, String authCode){

        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(authCode, email);
    }

    public String getAuthCodeEmail(String authCode){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String code = stringStringValueOperations.get(authCode);

        return code;
    }
}
