package com.birariro.visitknowledge.adapter.batch.step;

import com.birariro.visitknowledge.adapter.batch.step.event.BatchActionEvent;
import com.birariro.visitknowledge.adapter.message.event.Events;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import com.birariro.visitknowledge.service.registration.sync.LibrarySync;
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
import java.io.IOException;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncLibraryStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final CustomStepExecutionListener customStepExecutionListener;
    private final LibrarySync librarySync;


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

        List<Library> libraries = librarySync.getSyncLibrary();
        return new ListItemReader<>(libraries);
    }


    public ItemProcessor<Library, Library> syncLibraryProcessor(){
        return item ->{
            return item;
        };
    }

    public ItemWriter<Library> syncLibraryWriter(){
        return items -> {

            String addItemMessage = "";
            for (Library item : items) {
                if(libraryRepository.existsByName(item.getName())){
                    log.info("[syncLibraryStep] duplicate site name : "+item.getName());
                    continue;
                }
                addItemMessage += item.getName() + " ";
                libraryRepository.save(item);
            }
            if(addItemMessage.length() > 10){
                addItemMessage += "사이트가 새롭게 추가 되었습니다.";
                Events.raise(new BatchActionEvent(false,addItemMessage));
            }

        };
    }
}
