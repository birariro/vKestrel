package com.birariro.dailydevblogassemble.adapter.statistics;

import com.birariro.dailydevblogassemble.domain.member.event.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsSubscribe {

    @EventListener(NewRegistrationEvent.class)
    public void event(NewRegistrationEvent event){
        log.info("[통계] RegistrationEvent email : "+event.getEmail());
    }
}
