package com.birariro.vkestrel.service;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.staff.Staff;
import com.birariro.vkestrel.adapter.persistence.staff.StaffRepository;
import com.birariro.vkestrel.adapter.persistence.staff.StaffType;
import com.birariro.vkestrel.annotation.AopExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegStaffService {

    private final StaffRepository staffRepository;

    @Async
    @AopExecutionTime
    @Transactional
    public void registration(String token, String channel, StaffType type){

        save(token, channel, type);
    }


    @Transactional
    protected void save(String token, String channel, StaffType type){

        Staff staff = new Staff(token, channel, type);
        staffRepository.save(staff);
    }

    public void greetings(String message){
        Events.raise(new ActionEvent(false, message,false));
    }

}
