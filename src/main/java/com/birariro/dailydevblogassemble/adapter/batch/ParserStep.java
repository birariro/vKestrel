package com.birariro.dailydevblogassemble.adapter.batch;

import com.birariro.dailydevblogassemble.domain.library.Document;
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
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class ParserStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;

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
        libraries.forEach(item->{
            System.out.println("ParserBatch = " + item.toString());
        });
        return new ListItemReader<>(libraries);
    }


    public ItemProcessor<Library,Library> libraryProcessor(){
        return item ->{
            String s = UUID.randomUUID().toString();
            Document document = new Document("title" + s, "url" + s, "author " + s);
            item.addDocument(document);
            return item;
        };
    }

    public ItemWriter<Library> libraryWriter(){
        return items -> {
            libraryRepository.saveAll(items);
        };
    }
}
