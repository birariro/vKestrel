package com.birariro.visitknowledge.adapter.batch.step;


import com.birariro.visitknowledge.adapter.parser.ParserAdapter;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
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
public class DocumentParsingStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final LibraryRepository libraryRepository;
    private final ParserAdapter parserAdapter;
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
        return item ->{

            log.info("RSS parser target name : " +item.getName());

            List<Document> documents = parserAdapter
                    .getDocuments(item.getUrl(), item.getType());


            List<Document> newDocuments = documents.stream()
                                                    .filter(target -> item.existDocument(target) == false)
                                                    .collect(Collectors.toList());


            newDocuments.forEach(document -> {
                log.info("[add document] " + item.getName() + ": "+document.getTitle());
                item.addDocument(document);
            });
            return item;
        };
    }

    public ItemWriter<Library> libraryDocumentParsingWriter(){
        return items -> {
            libraryRepository.saveAll(items);
        };
    }
}
