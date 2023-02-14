package com.birariro.visitknowledge.repository;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SoftDeleteTest {

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    public void softDeleteQueryTest(){
        libraryRepository.findAll();
    }
}
