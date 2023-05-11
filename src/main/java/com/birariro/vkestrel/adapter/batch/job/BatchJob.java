package com.birariro.vkestrel.adapter.batch.job;

import com.birariro.vkestrel.adapter.batch.step.DeliveryStep;
import com.birariro.vkestrel.adapter.batch.step.ParsingStep;
import com.birariro.vkestrel.adapter.batch.step.SyncLibraryStepConfiguration;
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
    private final ParsingStep parsingStep;
    private final DeliveryStep deliveryStep;
    private final SyncLibraryStepConfiguration syncLibraryStepConfiguration;

    @Bean
    public Job startJob() throws IOException {
        return jobBuilderFactory.get("startJob")
                .start(syncLibraryStepConfiguration.syncLibraryStep())
                .next(parsingStep.libraryDocumentParsingStep())
                .next(deliveryStep.dailyDocumentExtractStep())
                .build();
    }


}
