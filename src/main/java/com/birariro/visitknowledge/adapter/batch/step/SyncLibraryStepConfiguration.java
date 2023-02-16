package com.birariro.visitknowledge.adapter.batch.step;

import com.birariro.visitknowledge.adapter.batch.step.event.BatchActionEvent;
import com.birariro.visitknowledge.adapter.batch.step.event.DailyDocumentEvent;
import com.birariro.visitknowledge.adapter.message.event.Events;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Document;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.UrlType;
import com.birariro.visitknowledge.controller.init.CompanyJsonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncLibraryStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final CustomStepExecutionListener customStepExecutionListener;


    @Bean
    public Step syncLibraryStep() throws IOException {
        return stepBuilderFactory.get("syncLibraryStep")
                .listener(customStepExecutionListener)
                .<Library, Library> chunk(100)
                .reader(syncLibraryReader())
                .processor(syncLibraryProcessor())
                .writer(syncLibraryWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<Library> syncLibraryReader() throws IOException {

        List<Library> libraries = new ArrayList<>();

        ClassPathResource companyResource = new ClassPathResource("company-library.json");

        ObjectMapper objectMapper = new ObjectMapper();
        CompanyJsonDto[] companyJsonDtoList = objectMapper.readValue(new FileReader(companyResource.getFile()) , CompanyJsonDto[].class);

        for (CompanyJsonDto companyJsonDto : companyJsonDtoList) {

            Library library = new Library(companyJsonDto.getName(), companyJsonDto.getUrl(), companyJsonDto.getHome(), UrlType.valueOf(companyJsonDto.getType()));
            libraries.add(library);
        }

        ClassPathResource alonResource = new ClassPathResource("alon-library.json");
        ObjectMapper objectMapper2 = new ObjectMapper();
        CompanyJsonDto[] alonJsonDtoList = objectMapper2.readValue(new FileReader(alonResource.getFile()) , CompanyJsonDto[].class);
        for (CompanyJsonDto alonJsonDto : alonJsonDtoList) {
            Library library = new Library(alonJsonDto.getName(), alonJsonDto.getUrl(), alonJsonDto.getHome(), UrlType.valueOf(alonJsonDto.getType()));
            libraries.add(library);
        }

        return new ListItemReader<>(libraries);
    }


    public ItemProcessor<Library, Library> syncLibraryProcessor(){
        return item ->{
            return item;
        };
    }

    public ItemWriter<Library> syncLibraryWriter(){
        return items -> {

            for (Library item : items) {
                if(libraryRepository.existsByName(item.getName())){
                    log.info("[syncLibraryStep] duplicate site name : "+item.getName());
                    continue;
                }
                libraryRepository.save(item);
            }
        };
    }
}
