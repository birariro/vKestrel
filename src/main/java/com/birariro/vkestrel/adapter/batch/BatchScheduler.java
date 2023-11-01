package com.birariro.vkestrel.adapter.batch;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.message.event.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchScheduler {
    @Qualifier("parsingJob")
    private final Job parsingJob;

    @Qualifier("deliveryJop")
    private final Job deliveryJop;
    private final JobLauncher jobLauncher;


    @Scheduled(cron="${setting.schedule.cron.parsing:0 0 9 * * ?}", zone="Asia/Seoul")
    public void executeParsingJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        log.info("parsingJob run");

        jobLauncher.run(parsingJob,new JobParametersBuilder()
                .addString("datetime", LocalDateTime.now().toString())
                .toJobParameters());
    }
    @Scheduled(cron="${setting.schedule.cron.delivery:0 0 8 * * ?}", zone="Asia/Seoul")
    public void executeDeliveryJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        log.info("deliveryJop run");

        jobLauncher.run(deliveryJop,new JobParametersBuilder()
            .addString("datetime", LocalDateTime.now().toString())
            .toJobParameters());
    }


    private String runMessage(){
        String message = "<!here> :smile: \n";

        String art =
            "```         _________________\n" +
                "         ⎢                ⎥\n" +
                "         ⎢    두근 두근     ⎥\n" +
                "         ⎢____    ________⎥\n" +
                "               \\/                 \n"+
                "             /\\_____/\\\n" +
                "            /  @   @  \\\n" +
                "           ( ==  ^  == )\n" +
                "            )         (\n" +
                "           (           )\n" +
                "          ( (  )   (  ) )\n" +
                "         (__(__)___(__)__)```";


        return message + art;
    }
}
