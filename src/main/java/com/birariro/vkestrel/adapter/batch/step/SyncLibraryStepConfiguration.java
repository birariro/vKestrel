package com.birariro.vkestrel.adapter.batch.step;

import com.birariro.vkestrel.adapter.batch.step.event.ActionEvent;
import com.birariro.vkestrel.adapter.message.event.Events;
import com.birariro.vkestrel.adapter.persistence.jpa.library.Library;
import com.birariro.vkestrel.adapter.persistence.jpa.library.LibraryRepository;
import com.birariro.vkestrel.service.sync.LibrarySync;
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
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncLibraryStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final LibraryRepository libraryRepository;
    private final CustomStepExecutionListener customStepExecutionListener;
    private final LibrarySync librarySync;

    private final int chunkSize = 100;
    @Bean
    public Step syncLibraryStep() throws IOException {
        return stepBuilderFactory.get("syncLibraryStep")
                .listener(customStepExecutionListener)
                .<Library, Library> chunk(chunkSize)
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


            List<? extends Library> activeLibrary = items.stream().filter(Library::isActive)
                .filter(library -> !libraryRepository.existsByName(library.getName()))
                .collect(Collectors.toList());


            List<String> inActiveLibraryNames = items.stream()
                                                .filter(item -> !item.isActive())
                                                .map(Library::getName)
                                                .collect(Collectors.toList());

            List<Library> inActiveLibrary = libraryRepository.findActiveByAll()
                .stream().filter(item -> inActiveLibraryNames.contains(item.getName()))
                .collect(Collectors.toList());


            if( ! activeLibrary.isEmpty()){
                libraryRepository.saveAll(activeLibrary);
                String newTitles = activeLibrary.stream().map(Library::getName).collect(Collectors.toList()).toString();
                Events.raise(new ActionEvent(false,newTitles + " 사이트가 새롭게 추가 되었습니다."));
            }

            if( ! inActiveLibrary.isEmpty()){
                for (Library library : inActiveLibrary) {
                    library.inActive();
                    libraryRepository.save(library);
                }

                String deleteTitles = inActiveLibrary.stream().map(Library::getName).collect(Collectors.toList()).toString();
                Events.raise(new ActionEvent(false,deleteTitles + " 사이트가 제거 되었습니다."));
            }
        };
    }
}
