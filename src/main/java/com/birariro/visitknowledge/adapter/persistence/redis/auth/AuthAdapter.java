package com.birariro.visitknowledge.adapter.persistence.redis.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthAdapter {

    private final RedisTemplate<String, UUID> redisTemplate;

    public void saveAuthCode(UUID id, String authCode){

        ValueOperations<String, UUID> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(authCode, id);
    }

    public UUID getAuthCodeMemberId(String authCode){
        ValueOperations<String, UUID> stringStringValueOperations = redisTemplate.opsForValue();
        UUID id = stringStringValueOperations.get(authCode);

        return id;
    }
}
