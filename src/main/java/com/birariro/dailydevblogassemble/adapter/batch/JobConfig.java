package com.birariro.dailydevblogassemble.adapter.batch;

import com.birariro.dailydevblogassemble.domain.library.Document;
import com.birariro.dailydevblogassemble.domain.library.Library;
import com.birariro.dailydevblogassemble.domain.library.LibraryRepository;
import com.birariro.dailydevblogassemble.domain.library.UrlType;
import com.birariro.dailydevblogassemble.domain.member.State;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
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
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("startJob")
                .start(jobStep())
                .build();
    }

    @Bean
    public Step jobStep(){
        return stepBuilderFactory.get("jobStep")
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
            System.out.println("item = " + item.toString());
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
