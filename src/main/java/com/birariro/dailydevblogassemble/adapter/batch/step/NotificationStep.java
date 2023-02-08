package com.birariro.dailydevblogassemble.adapter.batch.step;

import com.birariro.dailydevblogassemble.adapter.email.EmailAdapter;
import com.birariro.dailydevblogassemble.domain.library.Document;
import com.birariro.dailydevblogassemble.domain.library.Library;
import com.birariro.dailydevblogassemble.domain.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class NotificationStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final CustomStepExecutionListener customStepExecutionListener;
    private final EmailAdapter emailAdapter;

    @Bean
    public Step notificationJobStep(){
        return stepBuilderFactory.get("notificationJobStep")
                .listener(customStepExecutionListener)
                .<Document, Document> chunk(10)
                .reader(notificationReader())
                .processor(notificationProcessor())
                .writer(notificationWriter())
                .build();
    }


    @Bean
    @StepScope
    public ListItemReader<Document> notificationReader(){
        List<Library> libraries = libraryRepository.findAll();
        List<Document> collect = libraries.stream().flatMap(library -> library.getWaitDocuments().stream())
                .collect(Collectors.toList());

        if(collect.size() > 0){
            emailAdapter.toDayDocumentsSend(collect);
        }

        return new ListItemReader<>(collect);
    }


    public ItemProcessor<Document, Document> notificationProcessor(){
        return item ->{
            log.info("wait doc  >>>> "+item.getTitle());
            return item;
        };
    }

    public ItemWriter<Document> notificationWriter(){
        //todo chunk 사이즈 10 으로 인해 10개의 데이터만 수정되는 문제 해결
        return items -> {
            log.info("[notificationWriter] items count : "+items.size());
            for (Document item : items) {
                item.sendComplete();
            }
        };
    }

}
