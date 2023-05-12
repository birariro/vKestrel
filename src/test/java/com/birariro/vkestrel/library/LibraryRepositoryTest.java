package com.birariro.vkestrel.library;

import java.util.Optional;

import com.birariro.vkestrel.adapter.persistence.library.Library;
import com.birariro.vkestrel.adapter.persistence.library.LibraryRepository;
import com.birariro.vkestrel.adapter.persistence.library.ScriptType;

import org.junit.jupiter.api.Assertions;
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

    @Test
    public void findByName(){
        Optional<Library> context = libraryRepository.findFirstByName("토스랩");
        Assertions.assertTrue(context.isPresent());
    }

}
