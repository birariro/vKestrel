package com.birariro.dailydevblogassemble.adapter.batch.step;

import com.birariro.dailydevblogassemble.domain.library.Library;
import com.birariro.dailydevblogassemble.domain.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class NotificationStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final CustomStepExecutionListener customStepExecutionListener;

    @Bean
    public Step notificationJobStep(){
        return stepBuilderFactory.get("notificationJobStep")
                .listener(customStepExecutionListener)
                .<Library, Library> chunk(10)
                .reader(notificationReader())
                .processor(notificationProcessor())
                .writer(notificationWriter())
                .build();
    }


    @Bean
    @StepScope
    public ListItemReader<Library> notificationReader(){
        List<Library> libraries = libraryRepository.findAll();
        libraries.forEach(item->{
            System.out.println("ToDayDocumentBatch = " + item.toString());
        });
        return new ListItemReader<>(libraries);
    }


    public ItemProcessor<Library,Library> notificationProcessor(){
        return item ->{
            return item;
        };
    }

    public ItemWriter<Library> notificationWriter(){
        return items -> {
            return ;
        };
    }
}
