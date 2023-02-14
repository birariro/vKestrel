package com.birariro.visitknowledge.repository;

import com.birariro.visitknowledge.domain.library.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibraryRepositoryTest {

    @Autowired
    LibraryRepository libraryRepository;
    @Test
    public void findActiveByAllTest(){
        libraryRepository.findActiveByAll();
    }
}
