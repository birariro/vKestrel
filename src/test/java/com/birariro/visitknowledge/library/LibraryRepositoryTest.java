package com.birariro.visitknowledge.library;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.ScriptType;
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
        Library library = new Library("name", "url", "test", ScriptType.RSS);
        libraryRepository.save(library);
    }

}
