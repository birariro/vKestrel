package com.birariro.vkestrel.repository;

import com.birariro.vkestrel.adapter.persistence.jpa.library.Document;
import com.birariro.vkestrel.adapter.persistence.jpa.library.Library;
import com.birariro.vkestrel.adapter.persistence.jpa.library.LibraryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class DateCopy {

    @Autowired
    public LibraryRepository libraryRepository;


    @Test
    @DisplayName("document 2배 복사")

    public void document2Copy(){

        List<Library> activeByAll = libraryRepository.findActiveByAll();
        for (Library library : activeByAll) {

            List<Document> documents = library.getDocuments();

            List<Document> collect = documents.stream().map(item -> new Document(item.getTitle() + "copy", item.getUrl(), item.getAuthor()))
                    .collect(Collectors.toList());

            for (Document document : collect) {
                library.addDocument(document);
            }
            libraryRepository.save(library);
        }


    }
}
