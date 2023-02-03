package com.birariro.playday.service.statistics;

import com.birariro.playday.adapter.event.registration.NewRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberStatisticsService {

    @EventListener(NewRegistrationEvent.class)
    public void event(NewRegistrationEvent event){
        log.info("[통계] RegistrationEvent email : "+event.getEmail());
    }
}
