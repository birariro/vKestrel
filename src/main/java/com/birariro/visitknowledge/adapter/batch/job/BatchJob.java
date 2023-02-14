package com.birariro.visitknowledge.adapter.batch.job;

import com.birariro.visitknowledge.adapter.batch.step.DailyDocumentStep;
import com.birariro.visitknowledge.adapter.batch.step.DocumentParsingStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final DocumentParsingStep documentParsingStep;
    private final DailyDocumentStep dailyDocumentStep;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("startJob")
                .start(documentParsingStep.libraryDocumentParsingStep())
                .next(dailyDocumentStep.dailyDocumentExtractStep())
                .build();
    }


}
