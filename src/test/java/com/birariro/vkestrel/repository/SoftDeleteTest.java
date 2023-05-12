package com.birariro.vkestrel.repository;

import com.birariro.vkestrel.adapter.persistence.library.LibraryRepository;
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
