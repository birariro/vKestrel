package com.birariro.vkestrel.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.WebHook.WebHook;
import com.birariro.vkestrel.adapter.persistence.WebHook.WebHookRepository;
import com.birariro.vkestrel.annotation.AopExecutionTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegWebHookService {

    private final WebHookRepository webHookRepository;

    @Async
    @AopExecutionTime
    @Transactional
    public void registration(String url){

        WebHook webHook = new WebHook(url);
        webHookRepository.save(webHook);
        Events.raise(new RegistrationWebHookEvent(webHook));
    }

    @Transactional
    public void delete(String url){

        WebHook webHook = webHookRepository.findByUrl(url)
            .orElseThrow(() -> new IllegalStateException());

        webHook.inActive();
    }
}
