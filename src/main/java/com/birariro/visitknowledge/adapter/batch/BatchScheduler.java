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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;


    @Scheduled(cron="${setting.schedule.cron:0 0 9 * * ?}", zone="Asia/Seoul")
    public void executeJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        log.info("job run");

        String message = "<!here> 두근 두근 :smile:";
        Events.raise(new BatchActionEvent(false,message));
        jobLauncher.run(job,new JobParametersBuilder()
                .addString("datetime", LocalDateTime.now().toString())
                .toJobParameters());
    }
}
