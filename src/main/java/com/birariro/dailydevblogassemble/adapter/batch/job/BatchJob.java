package com.birariro.dailydevblogassemble.adapter.batch.job;

import com.birariro.dailydevblogassemble.adapter.batch.step.DailyDocumentExtractStep;
import com.birariro.dailydevblogassemble.adapter.batch.step.LibraryDocumentParsingStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final LibraryDocumentParsingStep libraryDocumentParsingStep;
    private final DailyDocumentExtractStep dailyDocumentExtractStep;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("startJob")
                .start(libraryDocumentParsingStep.libraryDocumentParsingStep())
                .next(dailyDocumentExtractStep.dailyDocumentExtractStep())
                .build();
    }


}
