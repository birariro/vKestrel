package com.birariro.visitknowledge.library;

import com.birariro.visitknowledge.domain.library.Library;
import com.birariro.visitknowledge.domain.library.LibraryRepository;
import com.birariro.visitknowledge.domain.library.UrlType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class LibraryRepositoryTest {

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    @DisplayName("findByAll Library")
    public void findByAll(){
        libraryRepository.findAll();
    }

    @Test
    public void saveData(){
        Library library = new Library("name", "url", "test", UrlType.RSS);
        libraryRepository.save(library);
    }

}
