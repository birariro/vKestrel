package com.birariro.visitknowledge.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birariro.visitknowledge.adapter.message.event.Events;
import com.birariro.visitknowledge.adapter.persistence.jpa.WebHook.WebHook;
import com.birariro.visitknowledge.adapter.persistence.jpa.WebHook.WebHookRepository;
import com.birariro.visitknowledge.annotation.AopExecutionTime;
import com.birariro.visitknowledge.domain.event.NewRegistrationEvent;

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
        Events.raise(new NewRegistrationEvent(webHook));
    }

    @Transactional
    public void delete(String url){

        WebHook webHook = webHookRepository.findByUrl(url)
            .orElseThrow(() -> new IllegalStateException());

        webHook.inActive();
    }
}
