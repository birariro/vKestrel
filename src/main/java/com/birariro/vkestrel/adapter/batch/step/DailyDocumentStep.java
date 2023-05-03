package com.birariro.vkestrel.adapter.batch.step;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.batch.step.event.DailyDocumentEvent;
import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.jpa.library.Document;
import com.birariro.vkestrel.adapter.persistence.jpa.library.Library;
import com.birariro.vkestrel.adapter.persistence.jpa.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 하루에 한번
 * 사용자들 에게 보낼 document 들을 찾는 배치
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class DailyDocumentStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final CustomStepExecutionListener customStepExecutionListener;

    @Value("${setting.document.max-size:20}")
    private int maxSize;
    @Bean
    public Step dailyDocumentExtractStep(){
        return stepBuilderFactory.get("dailyDocumentExtractStep")
                .listener(customStepExecutionListener)
                .<Document, Document> chunk(100)
                .reader(dailyDocumentExtractReader())
                .processor(dailyDocumentExtractProcessor())
                .writer(dailyDocumentExtractWriter())
                .build();
    }


    @Bean
    @StepScope
    public ListItemReader<Document> dailyDocumentExtractReader(){
        List<Library> libraries = libraryRepository.findActiveByAll();

        //보내지지 않은 글을 모두 가지고온다.
        List<Document> collect = libraries.stream()
                .flatMap(library -> library.getWaitDocuments().stream())
                .collect(Collectors.toList());

        //보내지지 않은 글중 maxSize 만큼 랜덤하게 얻는다
        Random random = new Random();
        List<Document> randomCollect = random.ints(0, collect.size())
                .distinct()
                .limit(maxSize)
                .mapToObj(collect::get)
                .collect(Collectors.toList());


        if(collect.size() > 0) Events.raise(new DailyDocumentEvent(randomCollect));
        else Events.raise(ActionEvent.message("오늘은 볼것이 없습니다."));

        return new ListItemReader<>(randomCollect);
    }


    public ItemProcessor<Document, Document> dailyDocumentExtractProcessor(){
        return item ->{
            log.info("wait doc  >>>> "+item.getTitle());
            return item;
        };
    }

    public ItemWriter<Document> dailyDocumentExtractWriter(){
        return items -> {
            log.info("[dailyDocumentExtractWriter] items count : "+items.size());
            for (Document item : items) {
                item.sendComplete();
            }
        };
    }

}
