package com.birariro.dailydevblogassemble.adapter.batch.job;

import com.birariro.dailydevblogassemble.adapter.batch.step.BatchDailyDocumentExtractStep;
import com.birariro.dailydevblogassemble.adapter.batch.step.BatchLibraryDocumentParsingStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final BatchLibraryDocumentParsingStep batchLibraryDocumentParsingStep;
    private final BatchDailyDocumentExtractStep batchDailyDocumentExtractStep;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("startJob")
                .start(batchLibraryDocumentParsingStep.libraryDocumentParsingStep())
                .next(batchDailyDocumentExtractStep.dailyDocumentExtractStep())
                .build();
    }


}
