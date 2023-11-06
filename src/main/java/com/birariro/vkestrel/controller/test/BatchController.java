package com.birariro.vkestrel.controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test/batch")
@RequiredArgsConstructor
public class BatchController {

    @Qualifier("parsingJob")
    private final Job parsingJob;

    @Qualifier("deliveryJop")
    private final Job deliveryJop;
    private final JobLauncher jobLauncher;

    @GetMapping("/parsing")
    public void batchParsingRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        jobLauncher.run(parsingJob,new JobParametersBuilder()
                .addString("datetime", LocalDateTime.now().toString())
                .toJobParameters());
    }
    @GetMapping("/delivery")
    public void batchDeliveryRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        jobLauncher.run(deliveryJop,new JobParametersBuilder()
            .addString("datetime", LocalDateTime.now().toString())
            .toJobParameters());
    }
}
