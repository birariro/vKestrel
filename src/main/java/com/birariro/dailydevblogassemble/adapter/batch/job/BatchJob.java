package com.birariro.dailydevblogassemble.adapter.batch.job;

import com.birariro.dailydevblogassemble.adapter.batch.step.NotificationStep;
import com.birariro.dailydevblogassemble.adapter.batch.step.ParserStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final ParserStep parserStep;
    private final NotificationStep notificationStep;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("startJob")
                .start(parserStep.parserJobStep())
                .next(notificationStep.notificationJobStep())
                .build();
    }


}
