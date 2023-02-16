package com.birariro.visitknowledge.adapter.batch.job;

import com.birariro.visitknowledge.adapter.batch.step.DailyDocumentStep;
import com.birariro.visitknowledge.adapter.batch.step.DocumentParsingStep;
import com.birariro.visitknowledge.adapter.batch.step.SyncLibraryStepConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class BatchJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final DocumentParsingStep documentParsingStep;
    private final DailyDocumentStep dailyDocumentStep;
    private final SyncLibraryStepConfiguration syncLibraryStepConfiguration;

    @Bean
    public Job startJob() throws IOException {
        return jobBuilderFactory.get("startJob")
                .start(syncLibraryStepConfiguration.syncLibraryStep())
                .next(documentParsingStep.libraryDocumentParsingStep())
                .next(dailyDocumentStep.dailyDocumentExtractStep())
                .build();
    }


}
