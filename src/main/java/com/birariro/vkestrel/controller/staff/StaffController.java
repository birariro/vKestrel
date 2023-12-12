package com.birariro.vkestrel.controller.staff;

import com.birariro.vkestrel.adapter.persistence.staff.StaffType;
import com.birariro.vkestrel.service.StaffService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "slack", description = "slack 연결")
@RestController
@RequiredArgsConstructor
@Slf4j
public class StaffController {

    private final StaffService staffService;

    @Operation(summary = "관리 메시지 봇 등록")
    @PostMapping("/knowledge/reg")
    public ResponseEntity slackKnowledgeRegistration(@RequestBody RegStaffRequest regStaffRequest){

        log.info("slack knowledge registration");
        staffService.append(
                regStaffRequest.getToken(), regStaffRequest.getChannel(), StaffType.KNOWLEDGE
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "관리 에러 봇 등록")
    @PostMapping("/error/reg")
    public ResponseEntity slackErrorRegistration(@RequestBody RegStaffRequest regStaffRequest){

        log.info("slack error registration");
        staffService.append(
                regStaffRequest.getToken(), regStaffRequest.getChannel(), StaffType.ERROR
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
