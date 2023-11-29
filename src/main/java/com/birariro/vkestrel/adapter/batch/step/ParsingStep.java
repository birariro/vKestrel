package com.birariro.vkestrel.adapter.batch.step;


import com.birariro.vkestrel.service.parser.ParserService;
import com.birariro.vkestrel.adapter.persistence.library.Document;
import com.birariro.vkestrel.adapter.persistence.library.Library;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 하루에 한번
 * 각 사이트에서 게시글을 파싱한다.
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ParsingStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final ParserService parserService;
    private final CustomStepExecutionListener customStepExecutionListener;

    private final int chunkSize = 10;
    @Bean
    public Step libraryDocumentParsingStep(){
        return stepBuilderFactory.get("libraryDocumentParsingStep")
                .listener(customStepExecutionListener)
                .<Library, Library> chunk(chunkSize)
                .reader(libraryDocumentParsingReader())
                .processor(libraryDocumentParsingProcessor())
                .writer(libraryDocumentParsingWriter())
                .build();
    }


    @Bean
    @StepScope
    public JpaPagingItemReader<Library> libraryDocumentParsingReader(){

        return new JpaPagingItemReaderBuilder<Library>()
                .queryString("select l from Library l where l.entityState = 'ACTIVE'")
                .pageSize(chunkSize)
                .entityManagerFactory(entityManagerFactory)
                .name("libraryPagingReader")
                .build();
    }


    public ItemProcessor<Library,Library> libraryDocumentParsingProcessor(){
        return library ->{

            log.info("[START] RSS parser target name : " +library.getName());

            List<Document> documents = parserService
                    .getDocuments(library.getName(), library.getUrl(), library.getScriptType());


            documents.stream()
                    .filter(target -> library.existDocument(target) == false)
                    .collect(Collectors.toList())
                    .forEach(document -> {
                        library.addDocument(document);
                    });

            log.info("[END] RSS parser target name : " + library.getName());
            return library;
        };
    }

    public ItemWriter<Library> libraryDocumentParsingWriter(){
        return items -> {
            //libraryRepository.saveAll(items);
        };
    }
}
