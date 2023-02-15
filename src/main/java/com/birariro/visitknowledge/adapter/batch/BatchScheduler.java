package com.birariro.visitknowledge.adapter.batch;

import com.birariro.visitknowledge.adapter.batch.step.event.BatchActionEvent;
import com.birariro.visitknowledge.adapter.message.event.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;

    //@Scheduled(fixedDelay = 600 * 1000L) // 10분
    @Scheduled(cron="0 43 15 * * ?", zone="Asia/Seoul")// 매일 오전 10시 0분 0초
    public void executeJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        log.info("job run");
        //Events.raise(new BatchActionEvent(false,"두근 두근"));
        jobLauncher.run(job,new JobParametersBuilder().addString("datetime", LocalDateTime.now().toString())
                .toJobParameters());
    }
}
