package com.birariro.dailydevblogassemble.adapter.batch;

import com.birariro.dailydevblogassemble.adapter.parser.RSSParser;
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

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ParserStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final RSSParser rssParser;

    @Bean
    public Step parserJobStep(){
        return stepBuilderFactory.get("parserJobStep")
                .<Library, Library> chunk(10)
                .reader(libraryReader())
                .processor(libraryProcessor())
                .writer(libraryWriter())
                .build();
    }


    @Bean
    @StepScope
    public ListItemReader<Library> libraryReader(){
        List<Library> libraries = libraryRepository.findAll();
        return new ListItemReader<>(libraries);
    }


    public ItemProcessor<Library,Library> libraryProcessor(){
        return item ->{

            List<Document> documents = rssParser.getDocument(item.getUrl());
            documents.forEach(document -> {
                log.info(item.getName() + ": "+document.getTitle());
                item.addDocument(document);
            });
            return item;
        };
    }

    public ItemWriter<Library> libraryWriter(){
        return items -> {
            libraryRepository.saveAll(items);
        };
    }
}
