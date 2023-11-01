package com.birariro.vkestrel.adapter.batch.job;

import com.birariro.vkestrel.adapter.batch.step.DeliveryStep;
import com.birariro.vkestrel.adapter.batch.step.ParsingStep;
import com.birariro.vkestrel.adapter.batch.step.SyncStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class BatchJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final ParsingStep parsingStep;
    private final DeliveryStep deliveryStep;
    private final SyncStep syncStep;

    @Bean
    @Qualifier("parsingJob")
    public Job parsingJob() throws IOException {
        return jobBuilderFactory.get("parsingJob")
                .start(syncStep.syncLibraryStep())
                .next(parsingStep.libraryDocumentParsingStep())
                .next(deliveryStep.dailyDocumentExtractStep())
                .build();
    }
    @Bean
    @Qualifier("deliveryJop")
    public Job deliveryJop()  {
        return jobBuilderFactory.get("deliveryJop")
            .start(deliveryStep.dailyDocumentExtractStep())
            .build();
    }


}
